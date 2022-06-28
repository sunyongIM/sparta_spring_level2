package com.example.level2.controller;

import com.example.level2.DTO.LikeDTO;
import com.example.level2.security.JwtProvider;
import com.example.level2.service.LikeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    private final LikeService likeService;
    private final JwtProvider jwtProvider;

    public LikeController(LikeService likeService, JwtProvider jwtProvider) {
        this.likeService = likeService;
        this.jwtProvider = jwtProvider;
    }

    // 게시글 좋아요 / 취소 (토글)
    @GetMapping("/api/board/{boardId}/like")
    public String like(@RequestHeader(value = "jwt") String header, @PathVariable Long boardId) {
        String email = jwtProvider.getUserPk(header);
//        LikeDTO likeDTO = new LikeDTO(email,boardId);
        LikeDTO likeDTO = LikeDTO.builder().
                userEmail(email)
                .boardId(boardId)
                .build();
        return likeService.toggleLike(likeDTO);
    }
}
