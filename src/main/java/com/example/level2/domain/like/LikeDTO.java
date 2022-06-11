package com.example.level2.domain.like;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class LikeDTO {
    private final Long _id;
    private final Long user_id;
    private final List<Long> board_ids;
}
