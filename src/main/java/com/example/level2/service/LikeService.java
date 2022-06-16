package com.example.level2.service;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardRepository;
import com.example.level2.domain.like.Like;
import com.example.level2.DTO.LikeDTO;
import com.example.level2.domain.like.LikeRepository;
import com.example.level2.domain.user.User;
import com.example.level2.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String toggleLike(LikeDTO likeDTO) {
        Board board = boardRepository.findById(likeDTO.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시판이 존재하지 않습니다")
        );
        User user = userRepository.findByEmail(likeDTO.getUserEmail()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 아이디가 존재하지 않습니다")
        );
        Like like = new Like();

        like.setLikeId(user);
        like.setLikeId(board);

        Optional<Like> likeOptional = likeRepository.findLikeByLikeIdAndBoardId(user, board);

        if (likeOptional.isPresent()) {
            likeRepository.deleteByLikeIdAndBoardId(user, board);
            return "좋아요 취소";
        } else {
            likeRepository.save(like);
            return "좋아요";
        }

    }

}
