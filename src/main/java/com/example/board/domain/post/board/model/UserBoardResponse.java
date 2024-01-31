package com.example.board.domain.post.board.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBoardResponse {

    private String userName;

    private String title;

    private String content;

    private LocalDateTime boardAt;

    private String status;


}
