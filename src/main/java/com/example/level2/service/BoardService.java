package com.example.level2.service;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.DTO.BoardResDTO;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardRepository;
import com.example.level2.domain.user.User;
import com.example.level2.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        /** 과제 요구사항 6
         * 1. 좋아요 순 정렬
         *     - 정렬 기준 중 하나를 선택해주세요!
         *         - 생성일 순  <-
         *         - 좋아요 순
         *         - view 순 */
        // 좋아요 순 정렬을 board 테이블에 좋아요 개수 컬럼을 만들지 않고 구현하려고 했다
        // 일반적인 JPA는 그런 동적인 기능을 제공하지 않아서 JPQL을 공부해서 구현하려 한다


        /* boardRepository를 이용하여 Board객체를 찾아 온 다음 원하는 형식으로 반환하기 위해 BoardResDTO로 변환한다 (toRes 메서드 사용) */
        List<BoardResDTO> result = new ArrayList<>();

        List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();

        for (Board boardObj : boardList) {
            result.add(BoardResDTO.toRes(boardObj));
        }
        return result;

        // 위의 코드와 같은 결과가 나온다
//        return boardRepository.findAll().stream().map(BoardResDTO::toRes).collect(Collectors.toList());
    }

    // 게시글 조회
    public BoardResDTO findBoard(Long board_id) {
        /* boardRepository를 이용하여 Board객체를 찾아 온 다음 원하는 형식으로 반환하기 위해 BoardResDTO로 변환한다 (toRes 메서드 사용) */
        return BoardResDTO.toRes(boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다")
        ));
    }

    // 게시글 삭제
    @Transactional
    public void removeBoard(Long board_id, String email) {
        /* token에서 찾아 온 email이 작성자 이메일과 같을 때만 삭제를 진행한다 */
        boardRepository.deleteBy_idAndUserEmail(board_id, email).orElseThrow(
                () -> new IllegalArgumentException("게시글의 작성자가 아닙니다")
        );
    }

    // 게시글 수정
    @Transactional
    public void modifyBoard(BoardReqDTO boardReqDTO) {

        Board prevBoard = boardRepository.findBy_idAndUserEmail(boardReqDTO.getBoardId(), boardReqDTO.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("게시글의 작성자가 아닙니다")
        );

        prevBoard.update(boardReqDTO);
    }

}
