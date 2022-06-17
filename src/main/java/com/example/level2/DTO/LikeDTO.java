package com.example.level2.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class LikeDTO {
    private String userEmail;
    private Long boardId;

    public LikeDTO( String userEmail, Long boardId){
        this.userEmail = userEmail;
        this.boardId = boardId;
    }

}
