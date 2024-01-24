package com.example.board.users.business;

import com.example.board.common.annotation.Business;
import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.ApiException;
import com.example.board.users.converter.UserConverter;
import com.example.board.users.model.UserRegisterRequest;
import com.example.board.users.model.UserResponse;
import com.example.board.users.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    /*
    사용자에 대한 가입처리 로직
    1. request -> entity
    2. entity -> save
    3. save Entity -> response
    4. response return
     */

    public UserResponse register(UserRegisterRequest request){
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;

//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "request null"));
    }
}
