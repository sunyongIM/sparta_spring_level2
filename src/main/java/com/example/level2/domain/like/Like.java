package com.example.level2.domain.like;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.user.User;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "LIKES")
@Entity
@Getter
@NoArgsConstructor
public class Like extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Board_id")
    private Board boardId;

    public Like(Long _id, User user, Board board){
        this._id = _id;
        this.likeId = user;
        this.boardId = board;
    }


    public void setLikeId(User user){
        this.likeId = user;
    }

    public void setLikeId(Board board){
        this.boardId = board;
    }

}
