package com.ballnet.user.service.repository;

import com.ballnet.user.service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);
}
