package com.example.level2.domain.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long writerId;
    private String img;
    private String content;
    private int layout;
}
