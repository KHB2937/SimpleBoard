package com.example.board.board.controller;

import com.example.board.board.db.BoardEntity;
import com.example.board.board.model.BoardDto;
import com.example.board.board.model.BoardRequest;
import com.example.board.board.service.BoardService;
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
            @RequestBody BoardRequest boardRequest
            ){
        return boardService.create(boardRequest);
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
