package com.example.level2.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeDTO {
    private String userEmail;
    private Long boardId;

    @Builder
    public LikeDTO( String userEmail, Long boardId){
        this.userEmail = userEmail;
        this.boardId = boardId;
    }

}
