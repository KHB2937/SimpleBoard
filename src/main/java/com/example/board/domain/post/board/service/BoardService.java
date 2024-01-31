package com.example.board.domain.post.board.service;

import com.example.board.domain.post.board.converter.BoardConverter;
import com.example.board.domain.post.board.db.BoardEntity;
import com.example.board.domain.post.board.db.BoardRepository;
import com.example.board.domain.post.board.model.BoardDto;
import com.example.board.domain.post.board.model.BoardRequest;
import com.example.board.domain.post.board.model.UserBoardRequest;
import com.example.board.domain.post.comment.db.CommentRepository;
import com.example.board.domain.users.db.UserEntity;
import com.example.board.domain.users.model.User;
import com.example.board.domain.users.model.UserResponse;
import com.example.board.domain.users.service.UserService;
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
    private final UserService userService;


    public BoardEntity create(
            BoardRequest boardRequest
    ){
        UserEntity userEntity = userService.getUserWithThrow(boardRequest.getUserName());

        var entity = BoardEntity.builder()
//                .user(userEntity)
                .userName(boardRequest.getUserName())
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .boardAt(LocalDateTime.now())
                .status("REGISTERED")
                .build()
                ;

        return boardRepository.save(entity);
    }

    public BoardEntity create(
            UserBoardRequest userboardRequest,
            UserResponse userResponse
    ){
        var entity = BoardEntity.builder()
//                .userName(String.valueOf(userboardRequest.getUserName()))
                .title(userboardRequest.getTitle())
                .content(userboardRequest.getContent())
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
