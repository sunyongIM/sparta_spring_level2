package com.example.level2.DTO;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResDTO {
    private Long _id;
    private String content;
    private Integer layout;
    private List<List<String>> likes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @Builder
    public BoardResDTO(Long _id, String content, Integer layout, List<List<String>> likes, LocalDateTime createdAt, LocalDateTime modifiedAt){
        this._id = _id;
        this.content = content;
        this.layout = layout;
        this.likes = likes;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static BoardResDTO toRes(Board board) {
        /** 좋아요 한 아이디 객체들을 이메일과 닉네임만 보이게 처리 */
        List<List<String>> likeIds = new ArrayList<>();
        for (Like likeObj : board.getLikeIds()) {
            List<String> tempList = new ArrayList<>();
            tempList.add(likeObj.getUserId().getEmail());
            tempList.add(likeObj.getUserId().getNickname());

            likeIds.add(tempList);
        }

        return BoardResDTO.builder().
                _id(board.get_id())
                .content(board.getContent())
                .layout(board.getLayout())
                .likes(likeIds)
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .build();

    }
}
