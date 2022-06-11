package com.example.level2.domain.like;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

//    @MapsId
    @OneToOne
    @JoinColumn(name = "User_id")
    private User like_id;

    @JsonManagedReference
    @OneToMany(mappedBy = "likeFK")
    private List<Board> board_ids;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Board_FK")
    private Board boardFK;

}
