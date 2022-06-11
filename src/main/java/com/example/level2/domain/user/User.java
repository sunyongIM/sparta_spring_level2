package com.example.level2.domain.user;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "USER")
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

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "writer_id", cascade = CascadeType.ALL)
    private Board board;

    @OneToOne(mappedBy = "like_id", cascade = CascadeType.ALL)
    private Like like;

    User(UserDTO userDTO) {
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
}
