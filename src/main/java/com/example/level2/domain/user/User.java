package com.example.level2.domain.user;

import com.example.level2.DTO.UserDTO;
import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "USER")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "writerId", cascade = CascadeType.ALL)
    private List<Board> boards;

    @OneToMany(mappedBy = "likeId", cascade = CascadeType.ALL)
    private List<Like> likes;

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.nickname = userDTO.getNickname();
        this.password = userDTO.getPassword();
    }

    public void update(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.nickname = userDTO.getNickname();
        this.password = userDTO.getPassword();
    }

    public void addBoard(Board board){
        board.setWriterId(this);
    }

    public void addLikes(Like like){
        like.setLikeId(this);
    }
}
