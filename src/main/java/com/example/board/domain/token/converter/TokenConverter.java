package com.example.board.domain.token.converter;

import com.example.board.common.annotation.Converter;
import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.ApiException;
import com.example.board.domain.token.model.TokenDto;
import com.example.board.domain.token.model.TokenResponse;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        Objects.requireNonNullElseGet(accessToken, ()->{
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        Objects.requireNonNullElseGet(refreshToken, ()->{
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
