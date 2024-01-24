package com.example.board.post.comment.service;

import com.example.board.post.board.db.BoardRepository;
import com.example.board.post.comment.converter.CommentConverter;
import com.example.board.post.comment.db.CommentEntity;
import com.example.board.post.comment.db.CommentRepository;
import com.example.board.post.comment.model.CommentDto;
import com.example.board.post.comment.model.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final CommentConverter commentConverter;


    /**
     * 댓글을 생성하는 메소드
     *
     * @param commentRequest 댓글 생성에 필요한 정보를 담은 DTO
     * @return 생성된 댓글 엔터티
     */
    public CommentEntity create(
            CommentRequest commentRequest
    ){
        // 주어진 boardId에 해당하는 BoardEntity를 찾음
        var boardEntity = boardRepository.findById(commentRequest.getBoardId()).get();

        // CommentEntity를 생성하고 저장
        var entity = CommentEntity.builder()
                .boardEntity(boardEntity)
                .content(commentRequest.getContent())
                .userName(commentRequest.getUserName())
                .commentAt(LocalDateTime.now())
                .status("REGISTERED")
                .build()
                ;
        return commentRepository.save(entity);
    }

    /**
     * 모든 댓글을 조회하여 DTO 리스트로 변환하는 메소드
     *
     * @return 모든 댓글을 담은 DTO 리스트
     */
    public List<CommentDto> all(){
        // 모든 댓글 엔터티를 조회
        List<CommentEntity> commentEntities = commentRepository.findAll();

        // 각 댓글 엔터티를 CommentDto로 변환하여 리스트에 추가
        return commentEntities.stream()
                .map(commentConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 댓글을 업데이트하는 메소드
     *
     * @param commentId      업데이트할 댓글의 고유 식별자
     * @param updatedComment 업데이트된 내용을 담은 DTO
     * @return 업데이트된 댓글 엔터티
     */

    public CommentEntity update(Long id, CommentRequest commentRequest){
        // 주어진 commentId에 해당하는 댓글을 찾음
        CommentEntity existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        // 기존 댓글의 내용(content), 업데이트 시간(commentAt), 상태(status) 등을 업데이트
        existingComment.setContent(commentRequest.getContent());
        existingComment.setCommentAt(LocalDateTime.now());
        existingComment.setStatus("UPDATE");

        return commentRepository.save(existingComment);
    }

    public void delete(Long id){
        // comment id 찾기
        CommentEntity commentDelete = commentRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("댓글이 없엉" + id));

        commentRepository.delete(commentDelete);
    }
}
