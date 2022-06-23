package com.example.level2.domain.board;

import com.example.level2.DTO.BoardReqDTO;
import com.example.level2.domain.Image.Image;
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
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

//    @Lob
//    @Column(nullable = false)
//    private String imageString;

    @Column(nullable = false, columnDefinition = "VARCHAR(3000)")
    private String content;

    @Column(nullable = false)
    private Integer layout;

    @OneToOne(mappedBy = "boardId")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User userId;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL)
    private List<Like> likeIds = new ArrayList<>();

    @Builder
    public Board(String content, Integer layout, Image image, User userId, List<Like> likeIds) {
        this.content = content;
        this.layout = layout;
        this.image = image;
        this.userId = userId;
        this.likeIds = likeIds;
    }

    public Board(BoardReqDTO boardReqDTO) {
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();
    }

    public void update(BoardReqDTO boardReqDTO) {
        this.content = boardReqDTO.getContent();
        this.layout = boardReqDTO.getLayout();

    }

    public void setUserId(User user) {
        this.userId = user;
    }

}
