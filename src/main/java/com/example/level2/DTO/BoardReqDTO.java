package com.example.level2.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardReqDTO {
    private Long writerId;
    private String img;
    private String content;
    private int layout;
}
