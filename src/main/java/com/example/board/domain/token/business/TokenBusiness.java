package com.example.board.domain.token.business;

import com.example.board.common.annotation.Business;
import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.ApiException;
import com.example.board.domain.token.converter.TokenConverter;
import com.example.board.domain.token.model.TokenResponse;
import com.example.board.domain.token.service.TokenService;
import com.example.board.domain.users.db.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /*
    1. user entity user id 추출
    2. access, refresh token 발행
    3. converter -> token response로 변경
    * */

    public TokenResponse issueToken(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(ue -> {
                    return ue.getId();
                })
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken){
        var userId = tokenService.validationToken(accessToken);
        return userId;
    }
}
