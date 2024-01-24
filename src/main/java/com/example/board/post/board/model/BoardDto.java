package com.example.board.post.board.model;

import com.example.board.post.comment.model.CommentDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDto {

    private Long id;

    private String userName;

    private String title;

    private String content;

    private LocalDateTime boardAt;

    private String status;

    private List<CommentDto> commentList = List.of();
}
