package com.example.level2.controller;

import com.example.level2.domain.user.User;
import com.example.level2.DTO.UserDTO;
import com.example.level2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/api/register")
    public void userAdd(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    // 로그인 (토큰)
    @PostMapping("/api/login")
    public String userLogin(@RequestBody UserDTO userDTO) {

        String token = userService.loginUser(userDTO);

        return token;
    }

//    // 로그인
//    @PostMapping("/api/login/")
//    public Boolean userLogin(@RequestBody UserDTO userDTO) {
//        return userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
//    }

    // 마이페이지
    @PostMapping("/api/mypage/")
    public User userDetails(@PathVariable Long userId) {
        return userService.detailsUser(userId);
    }

    // 회원정보 수정


}
