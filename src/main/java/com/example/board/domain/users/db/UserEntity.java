package com.example.board.domain.users.db;

import com.example.board.domain.users.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@Data
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String userName;

    @Column(length = 45, nullable = false)
    private String email;

    @Column(length = 45, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // string 타입의 enum 사용
    private UserStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
