package com.example.level2.controller;

import com.example.level2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    // 게시글 좋아요
    @GetMapping("/api/board/{boardId}/like")
    public void like(@PathVariable Long boardId){

    }

    // 게시글 좋아요 취소
    @GetMapping("/api/board/{boardId}/likeCancel")
    public void likeCancel(@PathVariable Long boardId){

    }

}
