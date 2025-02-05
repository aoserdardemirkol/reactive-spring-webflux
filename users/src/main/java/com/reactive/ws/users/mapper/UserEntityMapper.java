package com.reactive.ws.users.mapper;

import com.reactive.ws.users.data.UserEntity;
import com.reactive.ws.users.presentation.model.CreateUserRequest;
import com.reactive.ws.users.presentation.model.UserRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

// This class is only for test purpose
@Mapper
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserEntity toEntity(CreateUserRequest createUserRequest);

    UserRest toDto(UserEntity entity);
}