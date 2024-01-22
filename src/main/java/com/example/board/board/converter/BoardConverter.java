package com.example.board.board.converter;

import com.example.board.board.db.BoardEntity;
import com.example.board.board.model.BoardDto;
import com.example.board.comment.converter.CommentConverter;
import com.example.board.comment.db.CommentEntity;
import com.example.board.comment.model.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final CommentConverter commentConverter;

    public BoardDto toDto(BoardEntity boardEntity){

        var commentList = boardEntity.getCommentList()
                .stream()
                .map(commentEntity -> commentConverter.toDto(commentEntity))
                .collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .userName(boardEntity.getUserName())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .status(boardEntity.getStatus())
                .boardAt(boardEntity.getBoardAt())
                .commentList(commentList)
                .build()
                ;
    }
}
