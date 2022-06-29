package com.ballnet.user.service.service;

import com.ballnet.user.service.config.AppProperties;
import com.ballnet.user.service.entity.UserEntity;
import com.ballnet.user.service.exception.UserDuplicatedException;
import com.ballnet.user.service.mapper.UserMapper;
import com.ballnet.user.service.model.User;
import com.ballnet.user.service.observer.UserObserver;
import com.ballnet.user.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final AppProperties appProperties;

  private final PasswordEncoder passwordEncoder;

  private final List<UserObserver> userObservers;

  public User register(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new UserDuplicatedException();
    }
    log.debug("Add user: {}", user);

    var userEntity = UserEntity.builder()
        .username(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
        .build();

    var saved = userRepository.saveAndFlush(userEntity);

    userObservers.forEach((userObserver) -> {
      userObserver.add(UserMapper.INSTANCE.toModel(saved));
    });

    return UserMapper.INSTANCE.toModel(saved);
  }

  public Optional<User> getByUsername(String username) {
    var user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(UserMapper.INSTANCE.toModel(user.get()));
  }

  public User update(User updateUser) {
    log.debug("User: {}, update: {}",updateUser.getId(), updateUser);
    var userEntity = UserEntity.builder()
        .id(updateUser.getId())
        .username(updateUser.getUsername())
        .password(passwordEncoder.encode(updateUser.getPassword()))
        .build();

    var saved = userRepository.saveAndFlush(userEntity);

    userObservers.forEach((userObserver) -> {
      userObserver.update(UserMapper.INSTANCE.toModel(saved));
    });
    return UserMapper.INSTANCE.toModel(saved);
  }
}
