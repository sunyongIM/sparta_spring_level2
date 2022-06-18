package com.example.level2.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
public class BoardReqDTO {
    private Long _id;
    private String email;
    private String nickname;
    private String imageString;
    private String content;
    private Integer layout;

    public BoardReqDTO(Long _id, String email, String nickname, String imageString, String content, Integer layout) {
        this._id = _id;
        this.email = email;
        this.nickname = nickname;
        this.imageString = imageString;
        this.content = content;
        this.layout = layout;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
