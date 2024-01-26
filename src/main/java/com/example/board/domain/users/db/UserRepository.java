package com.example.board.domain.users.db;

import com.example.board.domain.users.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 주어진 userId와 status에 따라 내림차순으로 정렬된 첫 번째 UserEntity를 찾는 메서드
    // SQL 쿼리: SELECT * FROM user WHERE id = ? AND status = ? ORDER BY id DESC LIMIT 1;
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    // 주어진 email, password, status에 따라 내림차순으로 정렬된 첫 번째 UserEntity를 찾는 메서드
    // SQL 쿼리: SELECT * FROM user WHERE email = ? AND password = ? AND status = ? ORDER BY id DESC LIMIT 1;
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
