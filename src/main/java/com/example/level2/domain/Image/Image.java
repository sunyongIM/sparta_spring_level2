package com.example.level2.domain.Image;

import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "IMAGE")
@Entity
@Getter
@NoArgsConstructor
public class Image extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @OneToOne
    @JoinColumn(name = "Board_id")
    private Board boardId;

    @Column(nullable = true)
    private String imgName;

    @Column(nullable = true)
    private String imgMime;

    @Column(columnDefinition = "BLOB")
    @Lob
    private byte[] imgData;

    @Builder
    public Image(Board board, String imgName, String imgMime, byte[] imgData) {
        this.boardId = board;
        this.imgName = imgName;
        this.imgMime = imgMime;
        this.imgData = imgData;
    }

    public void setBoardId(Board board) {
        this.boardId = board;
    }

}
