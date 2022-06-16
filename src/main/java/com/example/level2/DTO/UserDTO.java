package com.example.level2.DTO;

import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import com.example.level2.domain.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class UserDTO {
    //    private String name;
    private String email;
    private String nickname;
    private String password;
    private List<Long> boards;
    private List<Long> likes;

    public UserDTO(String email, String nickname, String password, List<Long> boards, List<Long> likes) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.boards = boards;
        this.likes = likes;
    }

    // User 객체를 받아 UserDTO로 변환하여 return하기 위한 메서드
    public static UserDTO toUser(User user) {
        List<Long> boardList = new ArrayList<>();
        List<Long> likeList = new ArrayList<>();
        for(Board board : user.getBoards()){
            boardList.add(board.get_id());
        }
        for(Like like : user.getLikes()){
            likeList.add(like.get_id());
        }

        return UserDTO.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .boards(boardList)
                .likes(likeList)
                .build();

    }
}
