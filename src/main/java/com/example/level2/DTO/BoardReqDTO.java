package com.example.level2.DTO;

import lombok.*;

@Getter
@NoArgsConstructor
public class BoardReqDTO {
    private Long boardId;
    private String email;
    private String nickname;
    private String imageString;
    private String content;
    private Integer layout;

    @Builder
    public BoardReqDTO(Long boardId, String email, String nickname, String imageString, String content, Integer layout) {
        this.boardId = boardId;
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
