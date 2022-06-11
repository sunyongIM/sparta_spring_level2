package com.example.level2.domain.like;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Like extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "User_id")
    private User user_id;

    @JsonManagedReference
    @OneToMany(mappedBy = "likeFK")
    private List<Board> board_ids;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Board_FK")
    private Board boardFK;

}
