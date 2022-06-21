package com.example.level2.domain.board;

import com.example.level2.domain.Timestamped;
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

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String imgMime;

    @Column(columnDefinition ="BLOB")
    @Lob
    private byte[] imgData;

    @Builder
    public Image(Board boardId, String imgName, String imgMime, byte[] imgData) {
        this.boardId = boardId;
        this.imgName = imgName;
        this.imgMime = imgMime;
        this.imgData = imgData;
    }
}
