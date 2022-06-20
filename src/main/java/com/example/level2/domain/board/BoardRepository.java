package com.example.level2.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();

    Optional<Board> deleteBy_idAndUserEmail(Long _id, String email);
    Optional<Board> findBy_idAndUserEmail(Long _id, String email);
}
