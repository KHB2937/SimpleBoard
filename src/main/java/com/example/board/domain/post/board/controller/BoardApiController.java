package com.example.board.domain.post.board.controller;

import com.example.board.common.annotation.UserSession;
import com.example.board.domain.post.board.db.BoardEntity;
import com.example.board.domain.post.board.model.BoardDto;
import com.example.board.domain.post.board.model.BoardRequest;
import com.example.board.domain.post.board.service.BoardService;
import com.example.board.domain.users.db.UserEntity;
import com.example.board.domain.users.model.User;
import com.example.board.domain.users.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardEntity create(
            @Valid
            @RequestBody BoardRequest boardRequest,
            @UserSession User user
            ){
        return boardService.create(boardRequest, user);
    }

    @GetMapping("/all")
    public List<BoardDto> all(){
        return boardService.all();
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody BoardRequest boardRequest){
        return boardService.update(id, boardRequest).getId();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable Long id){
        boardService.delete(id);
    }
}
