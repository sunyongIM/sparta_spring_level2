package com.example.level2.domain.board;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.like.Like;
import com.example.level2.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOARD")
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "User_id")
    private User writer_id;

    @JsonManagedReference
    @OneToMany(mappedBy = "boardFK")
    private final List<Like> like_ids = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Like_FK")
    private Like likeFK;

    Board(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }

    public void update(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }
}
