package com.example.board.domain.token.ifs;

import com.example.board.domain.token.model.TokenDto;

import java.util.Map;
import java.util.Objects;

public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String, Object> data);

    TokenDto issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}
