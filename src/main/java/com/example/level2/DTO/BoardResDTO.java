package com.example.level2.DTO;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class BoardResDTO {
    private Long _id;
    private String imageLink;
    private String content;
    private Integer layout;
    private String userEmail;
    private String userNickname;
    private List<List<String>> likes;

    public BoardResDTO(Long _id, String imageLink, String content, Integer layout, String userEmail, String userNickname, List<List<String>> likes){
        this._id = _id;
        this.imageLink = imageLink;
        this.content = content;
        this.layout = layout;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.likes = likes;
    }

    public static BoardResDTO toRes(Board board) {
        /** 좋아요 한 아이디 객체들을 이메일과 닉네임만 보이게 처리 */
        List<List<String>> likeIds = new ArrayList<>();
        for (Like likeObj : board.getLikeIds()) {
            List<String> tempList = new ArrayList<>();
            tempList.add(likeObj.getLikeId().getEmail());
            tempList.add(likeObj.getLikeId().getNickname());

            likeIds.add(tempList);
        }

        return BoardResDTO.builder().
                _id(board.get_id())
                .imageLink(board.getImageString())
                .content(board.getContent())
                .layout(board.getLayout())
                .userEmail(board.getUserEmail())
                .userNickname(board.getUserNickname())
                .likes(likeIds)
                .build();

    }
}
