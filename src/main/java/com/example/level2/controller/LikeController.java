package com.example.level2.controller;

import com.example.level2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;


}
