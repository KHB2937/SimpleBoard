package com.example.board.domain.users.model;

import com.example.board.domain.users.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;

    private String userName;

    private String email;

    private String password;

    private UserStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

}