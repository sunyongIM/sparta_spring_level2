package com.example.level2.service;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardRepository;
import com.example.level2.domain.like.Like;
import com.example.level2.DTO.LikeDTO;
import com.example.level2.domain.like.LikeRepository;
import com.example.level2.domain.user.User;
import com.example.level2.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void toggleLike(LikeDTO likeDTO) {
        Board board = boardRepository.findById(likeDTO.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시판이 존재하지 않습니다")
        );
        User user = userRepository.findById(likeDTO.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 아이디가 존재하지 않습니다")
        );
        Like like = new Like();
        user.addLikes(like);
        board.addLikes(like);

        Optional<Like> likeOptional = likeRepository.findLikeByLikeIdAndBoardId(user, board);

        if(likeOptional.isPresent()){
            likeRepository.deleteByLikeIdAndBoardId(user, board);
        } else {
            likeRepository.save(like);
        }

    }

//    public void removeLike(Long boardId, UserDTO userDTO) {
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 게시판이 존재하지 않습니다")
//        );
//        User user = userRepository.findById(userDTO.get_id()).orElseThrow(
//                ()-> new IllegalArgumentException("해당하는 아이디가 존재하지 않습니다")
//        );
//        Like like = new Like();
//        user.addLikes(like);
//        board.addLikes(like);
//        likeRepository.deleteById(like.get_id());
//    }

}
