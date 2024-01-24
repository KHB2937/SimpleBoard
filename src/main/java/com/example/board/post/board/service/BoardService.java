package com.example.board.post.board.service;

import com.example.board.post.board.converter.BoardConverter;
import com.example.board.post.board.db.BoardEntity;
import com.example.board.post.board.db.BoardRepository;
import com.example.board.post.board.model.BoardDto;
import com.example.board.post.board.model.BoardRequest;
import com.example.board.post.comment.db.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardConverter boardConverter;

    public BoardEntity create(
            BoardRequest boardRequest
    ){
        var entity = BoardEntity.builder()
                .userName(boardRequest.getUserName())
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .boardAt(LocalDateTime.now())
                .status("REGISTERED")
                .build()
                ;

        return boardRepository.save(entity);
    }

    public List<BoardDto> all(){
        List<BoardEntity> boardEntities = boardRepository.findAll();

        return boardEntities.stream()
                .map(boardConverter::toDto)
                .collect(Collectors.toList());
    }

    public BoardEntity update(Long id, BoardRequest boardRequest){
        //board id에 해당하는 게시판 찾기
        BoardEntity boardUpdate = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시판을 못찾음: " + id));

        // 기존 게시판의 제목, 내용, 업데이트 시간, 상태 등을 업데이트
        boardUpdate.setTitle(boardRequest.getTitle());
        boardUpdate.setContent(boardRequest.getContent());
        boardUpdate.setBoardAt(LocalDateTime.now());
        boardUpdate.setStatus("UPDATE");

        return boardRepository.save(boardUpdate);
    }

    public void delete(Long id){
        // board id 찾기
        BoardEntity boardDelete = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시판이 없어: " + id));

        boardRepository.delete(boardDelete);
    }
}
