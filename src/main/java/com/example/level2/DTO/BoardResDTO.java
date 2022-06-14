package com.example.level2.DTO;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDTO {
    private Long _id;
    private String content;
    private int layout;
    private Long writerId;
    private List<Long> likeIds;

    public static BoardResDTO toRes(Board board){
        List<Long> likeIds = new ArrayList<>();
        for(Like likeObj : board.getLikeIds()){
            likeIds.add(likeObj.getLikeId().get_id());
        }

        return BoardResDTO.builder().
                _id(board.get_id())
                .content(board.getContent())
                .layout(board.getLayout())
                .writerId(board.getWriterId().get_id())
                .likeIds(likeIds)
                .build();

    }
}
