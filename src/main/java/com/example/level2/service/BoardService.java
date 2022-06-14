package com.example.level2.service;

import com.example.level2.domain.board.Board;
import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.domain.board.BoardRepository;
import com.example.level2.DTO.BoardResDTO;
import com.example.level2.domain.user.User;
import com.example.level2.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 게시글 추가
    public void addBoard(BoardReqDTO boardReqDTO) {
        User user = userRepository.findById(boardReqDTO.getWriterId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다")
        );
        Board board = new Board(boardReqDTO);
        user.addBoard(board);
        boardRepository.save(board);
    }

    // 게시글 전체 조회
    public List<BoardResDTO> findBoards() {
//        return boardRepository.findAll();
        return boardRepository.findAll().stream().map(BoardResDTO::toRes).collect(Collectors.toList());

    }

    // 게시글 조회
    public Board findBoard(Long board_id) {
        return boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다")
        );
    }

    // 게시글 삭제
    @Transactional
    public void removeBoard(Long board_id) {
        boardRepository.deleteById(board_id);
    }

    // 게시글 수정
    @Transactional
    public void modifyBoard(Long board_id, BoardReqDTO boardReqDTO) {
        Board prevBoard = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다")
        );
        prevBoard.update(boardReqDTO);
    }

}
