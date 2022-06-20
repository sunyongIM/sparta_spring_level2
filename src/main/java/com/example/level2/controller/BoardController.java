package com.example.level2.controller;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.DTO.BoardResDTO;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.Image;
import com.example.level2.security.JwtProvider;
import com.example.level2.service.BoardService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;
    private final JwtProvider jwtProvider;

    public BoardController(BoardService boardService, JwtProvider jwtProvider) {
        this.boardService = boardService;
        this.jwtProvider = jwtProvider;
    }

/*    // 게시글 추가 - ModelAttribute 사용 (프론트에서 form-data 를 file과 [BoardReqDTO 의 json 형식]으로 보내줘야 함)
    @PostMapping("/api/boards")
    public void boardAdd(@RequestHeader(value = "jwt") String header, @RequestParam("image") MultipartFile imgFile, @ModelAttribute("boardJson") BoardReqDTO boardReqDTO) {
        String email = jwtProvider.getUserPk(header);
    }*/

    // 게시글 추가 (img 파일 추가)
    /** S3 웹 스토리지를 사용하지 않고 DB에 이미지 파일을 저장 해봤다
     * 다음엔 S3에 이미지 파일을 저장 해 볼 것이다 */
    @PostMapping("/api/boards")
    public void boardAdd(@RequestHeader(value = "jwt") String header, @RequestParam("image") MultipartFile imgFile,
                         @RequestParam("content") String content, @RequestParam("layout") Integer layout) {
        // header의 jwt을 복호화하여 email을 추출한다
        String email = jwtProvider.getUserPk(header);
        String byteToString;
        // base64로 이미지를 String으로 변환
        try {
            byteToString = "data:image/png;base64," + new String(Base64.encodeBase64(imgFile.getBytes()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file : " + imgFile.getOriginalFilename());
        }

        // BoardReqDTO를 build하여 서비스에 전송한다
        boardService.addBoard(
                BoardReqDTO.builder()
                .imageString(byteToString)
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
    /** 과제 요구사항 4 - 로그인하지 않은 사용자도, 게시글 목록 조회는 가능하도록 하기 (WebSecurityConfig에 구현) */
    public BoardResDTO boardDetails(@PathVariable Long boardId) {
        return boardService.findBoard(boardId);
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{boardId}")
    public Long boardRemove(@RequestHeader(value = "jwt") String header, @PathVariable Long boardId) {
        String email = jwtProvider.getUserPk(header);
        boardService.removeBoard(boardId, email);
        return boardId;
    }

    // 게시글 수정
    @PutMapping("/api/board/{boardId}")
    public void boardModify(@RequestHeader(value = "jwt") String header, @PathVariable Long boardId, @RequestParam("image") MultipartFile imgFile,
                            @RequestParam("content") String content, @RequestParam("layout") Integer layout) {
        String email = jwtProvider.getUserPk(header);
        String byteToString;
        // base64로 이미지를 String으로 변환
        try {
            String mime = imgFile.getContentType();
            String name = imgFile.getOriginalFilename();
            byte[] data = imgFile.getBytes();

            Image image = Image.builder()
                    .imgMime(mime)
                    .imgName(name)
                    .imgData(data)
                    .build();

            byteToString = "data:image/png;base64," + new String(Base64.encodeBase64(imgFile.getBytes()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file : " + imgFile.getOriginalFilename());
        }

        boardService.modifyBoard(
                BoardReqDTO.builder()
                        .boardId(boardId)
                        .imageString(byteToString)
                        .email(email)
                        .content(content)
                        .layout(layout)
                        .build()
        );
    }

}
