package com.example.board.post.comment.controller;

import com.example.board.post.comment.db.CommentEntity;
import com.example.board.post.comment.model.CommentDto;
import com.example.board.post.comment.model.CommentRequest;
import com.example.board.post.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("")
    public CommentEntity create(
            @Valid
            @RequestBody CommentRequest commentRequest
            ){

        log.info(String.valueOf(commentRequest));
        return commentService.create(commentRequest);
    }

    @GetMapping("/all")
    public List<CommentDto> all() {
        return commentService.all();
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody CommentRequest commentRequest){
        return commentService.update(id, commentRequest).getId();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        commentService.delete(id);
    }
}
