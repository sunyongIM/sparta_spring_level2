package com.example.level2.controller;

import com.example.level2.DTO.LikeDTO;
import com.example.level2.security.JwtProvider;
import com.example.level2.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    private final LikeService likeService;
    private final JwtProvider jwtProvider;

    public LikeController(LikeService likeService, JwtProvider jwtProvider){
        this.likeService = likeService;
        this.jwtProvider = jwtProvider;
    }

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

    // 게시글 좋아요 / 취소 (토글)
    @GetMapping("/api/board/{boardId}/like")
    public String like(@RequestHeader String header, @PathVariable Long boardId){
        String email = jwtProvider.getUserPk(header);
        LikeDTO likeDTO = new LikeDTO(email,boardId);
        return likeService.toggleLike(likeDTO);
    }
}
