package com.example.level2.controller;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardDTO;
import com.example.level2.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 추가
    @PostMapping("/api/boards")
    public void boardAdd(@RequestBody BoardDTO boardDTO) {
        boardService.addBoard(boardDTO);
    }

    // 게시글 전체 조회
    @GetMapping("/api/boards")
    public List<Board> boardList() {
        return boardService.findBoards();
    }

    // 게시글 조회
    @GetMapping("/api/board/{boardId}")
    public Board boardDetails(@PathVariable Long boardId) {
        return boardService.findBoard(boardId);
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{boardId}")
    public void boardRemove(@PathVariable Long boardId) {
        boardService.removeBoard(boardId);
    }

    // 게시글 수정
    @PutMapping("/api/board/{boardId}")
    public void boardModify(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO) {
        boardService.modifyBoard(boardId, boardDTO);
    }

}
