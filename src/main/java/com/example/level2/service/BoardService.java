package com.example.level2.service;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.DTO.BoardResDTO;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardRepository;
import com.example.level2.domain.user.User;
import com.example.level2.domain.user.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public BoardService(UserRepository userRepository, BoardRepository boardRepository){
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    // 게시글 추가
    @Transactional
    public void addBoard(BoardReqDTO boardReqDTO) {

        User user = userRepository.findByEmail(boardReqDTO.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일이 존재하지 않습니다")
        );

        // 닉네임 정보만 받지 않았으므로 user객체에서 받아온다
        boardReqDTO.setNickname(user.getNickname());

        Board board = new Board(boardReqDTO);
        board.setWriterId(user);
        boardRepository.save(board);
    }

    // 게시글 전체 조회
    public List<BoardResDTO> findBoards() {
        /** boardRepository를 이용하여 Board객체를 찾아 온 다음 원하는 형식으로 반환하기 위해 BoardResDTO로 변환한다 (toRes 메서드 사용) */
        List<BoardResDTO> result = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();
        for (Board boardObj : boardList) {
            result.add(BoardResDTO.toRes(boardObj));
        }
        return result;

        // 위의 코드와 같은 결과가 나온다
//        return boardRepository.findAll().stream().map(BoardResDTO::toRes).collect(Collectors.toList());
    }

    // 게시글 조회
    public BoardResDTO findBoard(Long board_id) {
        /** boardRepository를 이용하여 Board객체를 찾아 온 다음 원하는 형식으로 반환하기 위해 BoardResDTO로 변환한다 (toRes 메서드 사용) */
        return BoardResDTO.toRes(boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다")
        ));
    }

    // 게시글 삭제
    @Transactional
    public void removeBoard(Long board_id, String email) {
        /** token에서 찾아 온 email이 작성자 이메일과 같을 때만 삭제를 진행한다 */
        boardRepository.deleteBy_idAndUserEmail(board_id, email).orElseThrow(
                () -> new IllegalArgumentException("게시글의 작성자가 아닙니다")
        );
    }

    // 게시글 수정
    @Transactional
    public void modifyBoard(Long boardId, MultipartFile imgFile, BoardReqDTO boardReqDTO) {

        Board prevBoard = boardRepository.findBy_idAndUserEmail(boardId, boardReqDTO.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("게시글의 작성자가 아닙니다")
        );
        try {
            String byteToString = "data:image/png;base64," + new String(Base64.encodeBase64(imgFile.getBytes()), StandardCharsets.UTF_8);
//            boardReqDTO.setImageByte(byteToString);
            boardReqDTO = BoardReqDTO.builder().imageString(byteToString).build();


        } catch (IOException e) {
            throw new RuntimeException("Could not store file : " + imgFile.getOriginalFilename());
        }
        prevBoard.update(boardReqDTO);
    }

}
