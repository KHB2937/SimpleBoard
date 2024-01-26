package com.example.board.domain.post.comment.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByBoardEntityId(Long boardId);
}
