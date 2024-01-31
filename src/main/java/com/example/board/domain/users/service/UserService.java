package com.example.board.domain.users.service;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.error.UserErrorCode;
import com.example.board.common.exception.ApiException;
import com.example.board.domain.users.db.UserEntity;
import com.example.board.domain.users.db.UserRepository;
import com.example.board.domain.users.enums.UserStatus;
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

//    public UserEntity register(UserEntity userEntity){
//
//        userEntity.setStatus(UserStatus.REGISTERED);
//        userEntity.setRegisteredAt(LocalDateTime.now());
//
//        return userRepository.save(userEntity);
//    }


    public UserEntity login(
            String email,
            String passowrd
    ){
        var entity = getUserWithThrow(email, passowrd);
        return entity;
    }

    public UserEntity getUserWithThrow(
            String email,
            String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
            Long userId
    ){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
            String userName
    ){
        return userRepository.findFirstByUserNameAndStatusOrderByIdDesc(
                userName,
                UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
