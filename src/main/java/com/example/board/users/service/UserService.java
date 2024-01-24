package com.example.board.users.service;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.ApiException;
import com.example.board.users.db.UserEntity;
import com.example.board.users.db.UserRepository;
import com.example.board.users.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/*
*  user 도메인 로직을 처리하는 서비스
* */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());

                    return userRepository.save(userEntity);
                }).orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }


}
