package com.ballnet.user.service.mapper;

import com.ballnet.user.service.entity.UserEntity;
import com.ballnet.user.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User toModel(UserEntity userEntity);
  UserEntity toEntity(User user);
}
