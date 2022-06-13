package com.example.level2.controller;

import com.example.level2.domain.user.UserDTO;
import com.example.level2.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;

//    // 게시글 좋아요 (토큰)
//    @GetMapping("/api/board/{boardId}/like")
//    public void like(@PathVariable Long boardId){
//        likeService.addLike(boardId);
//    }
//
//    // 게시글 좋아요 취소 (토큰)
//    @GetMapping("/api/board/{boardId}/likeCancel")
//    public void likeCancel(@PathVariable Long boardId){
//        likeService.removeLike(boardId);
//    }

    // 게시글 좋아요
    @GetMapping("/api/board/{boardId}/like")
    public void like(@PathVariable Long boardId, @RequestBody UserDTO userDTO){
        likeService.addLike(boardId, userDTO);
    }

    // 게시글 좋아요 취소
    @GetMapping("/api/board/{boardId}/likeCancel")
    public void likeCancel(@PathVariable Long boardId, @RequestBody UserDTO userDTO){
        likeService.removeLike(boardId, userDTO);
    }
}
