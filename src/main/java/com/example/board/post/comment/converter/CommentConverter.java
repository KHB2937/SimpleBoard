package com.example.board.post.comment.converter;

import com.example.board.post.comment.db.CommentEntity;
import com.example.board.post.comment.model.CommentDto;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter {

    public CommentDto toDto(CommentEntity commentEntity){
        return CommentDto.builder()
                .id(commentEntity.getId())
                .boardId(commentEntity.getBoardEntity().getId())
                .userName(commentEntity.getUserName())
                .content(commentEntity.getContent())
                .status(commentEntity.getStatus())
                .commentAt(commentEntity.getCommentAt())
                .build()
                ;
    }

}
