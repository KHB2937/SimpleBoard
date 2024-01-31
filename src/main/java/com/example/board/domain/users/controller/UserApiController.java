package com.example.board.domain.users.controller;

import com.example.board.common.annotation.UserSession;
import com.example.board.common.api.Api;
import com.example.board.domain.users.business.UserBusiness;
import com.example.board.domain.users.model.User;
import com.example.board.domain.users.model.UserName;
import com.example.board.domain.users.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
    ){
        var response = userBusiness.me(user);
        return Api.OK(response);
    }

    @GetMapping("/name")
    public Api<Optional<UserName>> name(
            @UserSession User user
    ){
        var response = userBusiness.boardResponse(user);
        return Api.OK(response);
    }

}
