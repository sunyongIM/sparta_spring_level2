package com.example.level2.domain.board;

import com.example.level2.domain.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "IMAGE")
@Entity
@Getter
@Builder
@NoArgsConstructor
public class Image extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "Board_id")
    private Board boardImage;

    @Column(nullable = false)
    private String imgName;

    @Column
    private String imgMime;

    @Column
    private Byte[] imgData;

    public Image(Long _id, Board board, String imgName, String imgMime, Byte[] imgData) {
        this._id = _id;
        this.boardImage = board;
        this.imgName = imgName;
        this.imgMime = imgMime;
        this.imgData = imgData;
    }
}
