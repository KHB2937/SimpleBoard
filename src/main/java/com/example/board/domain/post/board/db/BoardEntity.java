package com.example.board.domain.post.board.db;

import com.example.board.domain.post.comment.db.CommentEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime boardAt;

    private String status;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    @Where(clause = "status = 'REGISTERED'")
    @org.hibernate.annotations.OrderBy(clause = "id desc")
    private List<CommentEntity> commentList = List.of();
}
