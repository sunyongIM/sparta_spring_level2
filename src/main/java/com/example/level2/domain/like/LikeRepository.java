package com.example.level2.domain.like;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByLikeIdAndBoardId(User like, Board board);
    void deleteByLikeIdAndBoardId(User likeId, Board boardId);
}
