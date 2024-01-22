package com.example.board.board.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
