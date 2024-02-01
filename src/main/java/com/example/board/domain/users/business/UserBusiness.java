package com.example.board.domain.users.business;

import com.example.board.common.annotation.Business;
import com.example.board.domain.token.business.TokenBusiness;
import com.example.board.domain.token.model.TokenResponse;
import com.example.board.domain.users.converter.UserConverter;
import com.example.board.domain.users.model.*;
import com.example.board.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * 비즈니스 로직을 처리하는 클래스입니다.
 * 사용자에 대한 가입, 로그인, 사용자 정보 조회, 게시판 응답 등 다양한 비즈니스 로직을 담고 있습니다.
 */
@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자 가입 처리 로직입니다.
     * 1. 요청 정보로부터 사용자 엔티티를 생성합니다.
     * 2. 생성한 엔티티를 서비스를 통해 등록하고, 등록된 엔티티를 반환합니다.
     * 3. 등록된 엔티티를 응답 형태로 변환하여 반환합니다.
     *
     * @param request 사용자 가입 요청 정보
     * @return 가입된 사용자 정보를 담은 응답
     */
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
    }

    /**
     * 사용자 로그인 처리 로직입니다.
     * 1. 이메일과 비밀번호를 이용하여 사용자 체크를 수행합니다.
     * 2. 로그인 확인된 사용자 엔티티를 획득합니다.
     * 3. 획득한 사용자 엔티티를 토큰 비즈니스를 통해 토큰 생성하고, 생성된 토큰을 반환합니다.
     *
     * @param request 로그인 요청 정보 (이메일, 비밀번호)
     * @return 토큰 생성된 응답 정보
     */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    /**
     * 현재 로그인된 사용자의 정보를 조회하는 로직입니다.
     * 1. 로그인된 사용자 정보를 통해 사용자 엔티티를 획득합니다.
     * 2. 획득한 사용자 엔티티를 응답 형태로 변환하여 반환합니다.
     *
     * @param user 현재 로그인된 사용자 정보
     * @return 현재 로그인된 사용자 정보를 담은 응답
     */
    public UserResponse me(User user) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var response = userConverter.toResponse(userEntity);
        return response;
    }

    /**
     * 게시판 응답을 위한 사용자 정보를 조회하는 로직입니다.
     * 1. 로그인된 사용자의 사용자명을 통해 사용자 엔티티를 획득합니다.
     * 2. 획득한 사용자 엔티티를 게시판 응답 형태로 변환하여 Optional로 반환합니다.
     *
     * @param user 로그인된 사용자 정보
     * @return 게시판 응답을 위한 사용자 정보를 담은 Optional
     */
    public Optional<UserName> boardResponse(User user) {
        var userEntity = userService.getUserWithThrow(user.getUserName());
        var response = userConverter.boardResponse(userEntity);
        System.out.println("response: " + response);
        return response;
    }
}
