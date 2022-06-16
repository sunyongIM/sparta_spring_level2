package com.example.level2.controller;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.DTO.BoardResDTO;
import com.example.level2.security.JwtProvider;
import com.example.level2.service.BoardService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;
    private final JwtProvider jwtProvider;

    public BoardController(BoardService boardService, JwtProvider jwtProvider) {
        this.boardService = boardService;
        this.jwtProvider = jwtProvider;
    }

/*    // 게시글 추가
    @PostMapping("/api/boards")
    public void boardAdd(@RequestBody BoardReqDTO boardReqDTO) {
        boardService.addBoard(boardReqDTO);
    }*/

/*    // 게시글 추가 - ModelAttribute 사용 (프론트에서 form-data 를 file과 [BoardReqDTO 의 json 형식]으로 보내줘야 함)
    @PostMapping("/api/boards")
    public void boardAdd(@RequestHeader String header, @ModelAttribute("image") MultipartFile imgFile, @ModelAttribute("boardJson") BoardReqDTO boardReqDTO) {
        String email = jwtProvider.getUserPk(header);

    }*/


    // 게시글 추가 (img 파일 추가)
    /*@PostMapping("/api/boards")
    public void boardAdd(@RequestHeader String header, @RequestParam("image") MultipartFile imgFile, @RequestParam("content") String content, @RequestParam("layout") Integer layout) {
        String email = jwtProvider.getUserPk(header);
        boardService.addBoard(imgFile, BoardReqDTO.builder()
                .email(email)
                .content(content)
                .layout(layout)
                .build()
        );
    }
*/

    // 게시글 추가 (img 파일 추가)
    @PostMapping("/api/boards")
    public void boardAdd(@RequestHeader(value = "Authorization") String header, @RequestParam("image") MultipartFile imgFile,
                         @RequestParam("content") String content, @RequestParam("layout") Integer layout) {
        String email = jwtProvider.getUserPk(header);
        boardService.addBoard(
                imgFile,
                BoardReqDTO.builder()
                .email(email)
                .content(content)
                .layout(layout)
                .build()
        );
    }

    // 게시글 전체 조회
    @GetMapping("/api/boards")
    public List<BoardResDTO> boardList() {
        return boardService.findBoards();
    }

    // 게시글 조회
    @GetMapping("/api/board/{boardId}")
    public BoardResDTO boardDetails(@PathVariable Long boardId) {
        return boardService.findBoard(boardId);
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{boardId}")
    public Long boardRemove(@RequestHeader(value = "Authorization") String header, @PathVariable Long boardId) {
        String email = jwtProvider.getUserPk(header);
        boardService.removeBoard(boardId, email);
        return boardId;
    }

    // 게시글 수정
    @PutMapping("/api/board/{boardId}")
    public void boardModify(@RequestHeader(value = "Authorization") String header, @PathVariable Long boardId, @RequestParam("image") MultipartFile imgFile,
                            @RequestParam("content") String content, @RequestParam("layout") Integer layout) {
        String email = jwtProvider.getUserPk(header);
        boardService.modifyBoard(
                imgFile,
                BoardReqDTO.builder()
                        ._id(boardId)
                        .email(email)
                        .content(content)
                        .layout(layout)
                        .build()
        );
    }

}
