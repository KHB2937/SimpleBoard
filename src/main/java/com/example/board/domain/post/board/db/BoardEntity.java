package com.example.board.domain.post.board.db;

import com.example.board.domain.post.comment.db.CommentEntity;
import com.example.board.domain.users.db.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @ManyToOne
//    @JsonIgnore
//    @ToString.Exclude
//    @JoinColumn(name = "users_id") // user_id는 BoardEntity의 외래키
//    private UserEntity user;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    @Where(clause = "status = 'REGISTERED'")
    @org.hibernate.annotations.OrderBy(clause = "id desc")
    private List<CommentEntity> commentList = List.of();
}
