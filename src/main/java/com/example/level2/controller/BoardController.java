package com.example.level2.controller;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardDTO;
import com.example.level2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    // 게시글 목록 가져오기
    @GetMapping("/api/boards")
    public List<Board> getBoards() {
        return new ArrayList<>();
    }

    // 게시글 추가
    @PostMapping("/api/boards")
    public void addBoards(@RequestBody BoardDTO boardDTO) {
    }

    // 게시글 조회
    @GetMapping("/api/board/{boardId}")
    public Board getBoard(@PathVariable Long boardId) {
        return new Board();
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
    }

    // 게시글 수정
    @PutMapping("/api/board/{boardId}")
    public Board updateBoard(@PathVariable Long boardId) {
        return new Board();
    }


}
