package com.ballnet.user.service.service;

import com.ballnet.user.service.model.Credential;
import com.ballnet.user.service.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ballnet.user.service.utils.Constants.JWT_TOKEN_INDEX;
import static com.ballnet.user.service.utils.Constants.PREFIX_TOKEN;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  private final TokenService tokenService;

  public Optional<User> authentication(Credential credential) {
    var user = userService.getByUsername(credential.getUsername());

    if (user.isEmpty()) {
      return Optional.empty();
    }

    if (passwordEncoder.matches(credential.getPassword(), user.get().getPassword())) {
      return user;
    }

    return Optional.empty();
  }

  public Optional<String> validateToken(String authHeader){
    if (authHeader == null || !authHeader.startsWith(PREFIX_TOKEN)) {
      return Optional.empty();
    }

    var tokenParts = authHeader.split(PREFIX_TOKEN);
    var jwtToken = tokenParts[JWT_TOKEN_INDEX];
    var user = tokenService.validate(jwtToken);
    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of("roles");
  }
}
