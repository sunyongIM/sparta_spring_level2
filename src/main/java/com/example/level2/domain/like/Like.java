package com.example.level2.domain.like;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.user.User;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Board_id")
    private Board boardId;

    @Builder
    public Like(User userId, Board boardId){
        this.userId = userId;
        this.boardId = boardId;
    }

//    public void setuserId(User user){
//        this.userId = user;
//    }
//
//    public void setuserId(Board board){
//        this.boardId = board;
//    }

}
