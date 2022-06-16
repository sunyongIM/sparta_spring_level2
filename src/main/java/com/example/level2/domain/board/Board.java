package com.example.level2.domain.board;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.domain.Timestamped;
import com.example.level2.domain.like.Like;
import com.example.level2.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "BOARD")
@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Lob
    @Column(nullable = false)
    private String imageLink;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private Integer layout;

    public Board(Long _id, String imageLink, String content, String userEmail, String userNickname, Integer layout){
        this._id = _id;
        this.imageLink = imageLink;
        this.content = content;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.layout = layout;
    }

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User writerId;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL)
    private final List<Like> likeIds = new ArrayList<>();

    public Board(BoardReqDTO boardReqDTO) {
        this.imageLink = boardReqDTO.getImageByte();
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();
    }

    public void update(BoardReqDTO boardReqDTO) {
        this.imageLink = boardReqDTO.getImageByte();
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();
    }

    public void setWriterId(User user) {
        this.writerId = user;
    }
}
