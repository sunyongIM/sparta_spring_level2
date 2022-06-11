package com.example.level2.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardDTO {
    private final Long _id;
    private final String title;
    private final String content;
    private final Long user_id;

}
