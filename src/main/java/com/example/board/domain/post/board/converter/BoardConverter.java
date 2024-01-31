package com.example.board.domain.post.board.converter;

import com.example.board.domain.post.board.db.BoardEntity;
import com.example.board.domain.post.board.model.BoardDto;
import com.example.board.domain.post.board.model.UserBoardRequest;
import com.example.board.domain.post.board.model.UserBoardResponse;
import com.example.board.domain.post.comment.converter.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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

//    public UserBoardResponse toResponse(UserBoardRequest userBoardRequest){
//        return Optional.ofNullable(userBoardRequest)
//                .map(it -> {
//                    return UserBoardResponse.builder()
//                            .userName()
//                            .title()
//                            .content()
//                            .boardAt(LocalDateTime.now())
//                            .status()
//                            .build();
//                })
//    }
}
