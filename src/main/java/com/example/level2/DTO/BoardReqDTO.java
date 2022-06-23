package com.example.level2.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/*@Getter
@NoArgsConstructor
public class BoardReqDTO {
    private Long boardId;
    private String imageString;
    private String content;
    private Integer layout;

    @Builder
    public BoardReqDTO(Long boardId, String imageString, String content, Integer layout) {
        this.boardId = boardId;
        this.imageString = imageString;
        this.content = content;
        this.layout = layout;
    }
}*/

@Getter
@NoArgsConstructor
public class BoardReqDTO {
    private MultipartFile image;
    private String content;
    private Integer layout;

    @Builder
    public BoardReqDTO(MultipartFile image, String content, Integer layout) {
        this.image = image;
        this.content = content;
        this.layout = layout;
    }

}