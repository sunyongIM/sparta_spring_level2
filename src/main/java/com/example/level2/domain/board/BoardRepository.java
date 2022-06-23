package com.example.level2.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();

    Optional<Board> findBy_id(Long _id);

    Optional<Board> deleteBy_id(Long _id);

    @Query(value = "select b " +
            "from Board as b left join Like as l " +
            "where b = l.boardId " +
            "group by b " +
            "order by count(b.likeIds)")
    List<Board> findAllByOrOrderByLikeIdsCount();

}
