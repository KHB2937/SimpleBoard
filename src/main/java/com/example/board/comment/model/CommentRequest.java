package com.example.board.comment.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentRequest {


    private Long boardId = 1L;

    @NotBlank
    private String userName;

    @NotBlank
    private String content;
}
