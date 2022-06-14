package com.example.level2.domain.board;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.domain.Timestamped;
import com.example.level2.domain.like.Like;
import com.example.level2.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "BOARD")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int layout;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User writerId;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL)
    private final List<Like> likeIds = new ArrayList<>();


    public Board(BoardReqDTO boardReqDTO) {
        this.img = boardReqDTO.getImg();
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();
    }

    public void update(BoardReqDTO boardReqDTO) {
        this.img = boardReqDTO.getImg();
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();
    }

    public void setWriterId(User user) {
        this.writerId = user;
    }

    public void addLikes(Like like){
        like.setLikeId(this);
    }

}
