package com.example.level2.controller;

import com.example.level2.DTO.UserDTO;
import com.example.level2.security.JwtProvider;
import com.example.level2.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider){
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    // 회원 가입
    @PostMapping("/api/register")
    public void userAdd(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    // 로그인 (토큰 반환)
    @PostMapping("/api/login")
    public String userLogin(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO);
    }

//    // 로그인
//    @PostMapping("/api/login/")
//    public Boolean userLogin(@RequestBody UserDTO userDTO) {
//        return userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
//    }

    // 마이페이지
    @GetMapping("/api/mypage/")
    public UserDTO userDetails(@RequestHeader(value = "jwt") String header) {
        String email = jwtProvider.getUserPk(header);
        return userService.detailsUser(email);
    }

    // 회원정보 수정
    @GetMapping("/api/mypage/alter")
    public UserDTO userAlter(@RequestHeader(value = "jwt") String header, @RequestBody UserDTO userDTO) {
        String email = jwtProvider.getUserPk(header);
        return userService.alterUser(email, userDTO);
    }

}
