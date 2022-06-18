package com.example.level2.domain.board;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.domain.Timestamped;
import com.example.level2.domain.like.Like;
import com.example.level2.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "BOARD")
@Entity
@Getter
@Builder
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Lob
    @Column(nullable = false)
    private String imageString;

    @Column(nullable = false)
    private String content;

    @Column
    private String userEmail;

    @Column
    private String userNickname;

    @Column(nullable = false)
    private Integer layout;

    @Column
    @OneToOne(mappedBy = "boardImage")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User writerId;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL)
    private List<Like> likeIds = new ArrayList<>();

//    @Column
//    private Integer likeCount;


    public Board(Long _id, String imageString, String content, String userEmail, String userNickname, Integer layout, User writerId, List<Like> likeIds){
        this._id = _id;
        this.imageString = imageString;
        this.content = content;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.layout = layout;
        this.writerId = writerId;
        this.likeIds = likeIds;
    }

    public Board(BoardReqDTO boardReqDTO) {
        this.userEmail = boardReqDTO.getEmail();
        this.userNickname = boardReqDTO.getNickname();
        this.imageString = boardReqDTO.getImageString();
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();
    }

    public void update(BoardReqDTO boardReqDTO) {
        this.imageString = boardReqDTO.getImageString();
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();

    }

    public void setWriterId(User user) {
        this.writerId = user;
    }
}
