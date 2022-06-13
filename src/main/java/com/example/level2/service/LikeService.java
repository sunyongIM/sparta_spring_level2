package com.example.level2.service;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.board.BoardRepository;
import com.example.level2.domain.like.Like;
import com.example.level2.domain.like.LikeRepository;
import com.example.level2.domain.user.User;
import com.example.level2.domain.user.UserDTO;
import com.example.level2.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // Like 엔티티가 관계의 주인이므로
    public void addLike(Long boardId, UserDTO userDTO) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시판이 존재하지 않습니다")
        );
        User user = userRepository.findById(userDTO.get_id()).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 아이디가 존재하지 않습니다")
        );
        Like like = new Like();
        like.setLikeId(user);
        board.getLikeIds().add(like);
        likeRepository.save(like);
    }

    public void removeLike(Long boardId, UserDTO userDTO) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시판이 존재하지 않습니다")
        );
        User user = userRepository.findById(userDTO.get_id()).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 아이디가 존재하지 않습니다")
        );
    }

}
