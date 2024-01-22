package com.example.board.comment.db;

import com.example.board.board.db.BoardEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    private String userName;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime commentAt;

    private String status;
}
