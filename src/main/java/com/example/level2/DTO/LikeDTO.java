package com.example.level2.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDTO {
//    private Long userId;
    private String userEmail;
    private Long boardId;

    public LikeDTO(String userEmail, Long boardId){
//        this.userId = userId;
        this.userEmail = userEmail;
        this.boardId = boardId;
    }
}
