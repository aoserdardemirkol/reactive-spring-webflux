package com.reactive.ws.reactive_spring_webflux.mapper;

import com.reactive.ws.reactive_spring_webflux.data.UserEntity;
import com.reactive.ws.reactive_spring_webflux.presentation.model.CreateUserRequest;
import com.reactive.ws.reactive_spring_webflux.presentation.model.UserRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

// This class is only for test purpose
@Mapper
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserEntity toEntity(CreateUserRequest createUserRequest);

    UserRest toDto(UserEntity entity);
}