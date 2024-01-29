package com.example.board.domain.users.converter;

import com.example.board.common.annotation.Converter;
import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.ApiException;
import com.example.board.domain.users.db.UserEntity;
import com.example.board.domain.users.model.UserResponse;
import com.example.board.domain.users.model.UserRegisterRequest;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it ->{
                    // to entity
                    return UserEntity.builder()
                            .userName(request.getUserName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .build()
                            ;
                }).orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));

    }

    public UserResponse toResponse(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
            .map(it -> {
                return UserResponse.builder()
                        .id(userEntity.getId())
                        .userName(userEntity.getUserName())
                        .email(userEntity.getEmail())
                        .status(userEntity.getStatus())
                        .registeredAt(userEntity.getRegisteredAt())
                        .unregisteredAt(userEntity.getUnregisteredAt())
                        .build();

            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));

    }
}
