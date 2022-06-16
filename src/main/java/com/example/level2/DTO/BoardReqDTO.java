package com.example.level2.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
public class BoardReqDTO {
    private Long _id;
    private String email;
    private String imageByte;
    private String content;
    private Integer layout;

    public BoardReqDTO(Long _id, String email, String imageByte, String content, Integer layout) {
        this._id = _id;
        this.email = email;
        this.imageByte = imageByte;
        this.content = content;
        this.layout = layout;
    }
}
