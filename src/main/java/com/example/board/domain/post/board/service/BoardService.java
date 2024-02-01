package com.example.board.domain.post.board.service;

import com.example.board.domain.post.board.converter.BoardConverter;
import com.example.board.domain.post.board.db.BoardEntity;
import com.example.board.domain.post.board.db.BoardRepository;
import com.example.board.domain.post.board.model.BoardDto;
import com.example.board.domain.post.board.model.BoardRequest;
import com.example.board.domain.post.comment.db.CommentRepository;
import com.example.board.domain.token.helper.JwtTokenHelper;
import com.example.board.domain.users.converter.UserConverter;
import com.example.board.domain.users.db.UserEntity;
import com.example.board.domain.users.db.UserRepository;
import com.example.board.domain.users.model.User;
import com.example.board.domain.users.model.UserName;
import com.example.board.domain.users.model.UserResponse;
import com.example.board.domain.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardConverter boardConverter;
    private final UserService userService;
    private final UserConverter userConverter;

    public BoardEntity create(
            @Valid BoardRequest userboardRequest,
            User user
    ){
        var userEntity = userService.getUserWithThrow(user.getUserName());
        // UserEntity를 통해 Optional<UserName>을 얻어옵니다.
        Optional<UserName> response = userConverter.boardResponse(userEntity);
        System.out.println("response: " + response);

        // Optional<UserName>에 값이 있는 경우 해당 값을 얻어오고, 없는 경우 예외를 발생시킵니다.
        String userName;
        if (response.isPresent()) {
            // 값이 존재하는 경우, UserName 객체에서 사용자 이름을 추출합니다.
            userName = response.get().getUserName();
        } else {
            // 값이 없는 경우, RuntimeException을 발생시켜 예외 처리합니다.
            throw new RuntimeException("사용자 이름이 없습니다.");
        }

        var entity = BoardEntity.builder()
                .userName(userName)
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
