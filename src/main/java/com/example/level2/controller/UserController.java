package com.example.level2.controller;

import com.example.level2.DTO.UserDTO;
import com.example.level2.security.JwtProvider;
import com.example.level2.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

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
    @ResponseStatus
    public void userAdd(@RequestBody UserDTO userDTO) throws Exception {
        /** 과제 요구사항 2
         * - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
         * - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
         * - 비밀번호 확인은 비밀번호와 정확하게 일치하기 */
        // 추후에 AOP에 대해 공부한 후 AOP를 사용하여 예외처리 해볼 것
        String nickname = userDTO.getNickname();
        String password = userDTO.getPassword();

        // 닉네임이 너무 길어질 것에 대비하여 12자로 제한
        String nicknamePattern = "^[a-zA-Z0-9]{3,12}$";
        // 닉네임을 포함하지 않는 정규식은 작성하였지만, 4자 이상 조건 X
        String passwordPattern = "^((?!"+nickname+").)*$";

        // 프론트에 어떻게 에러를 반환하는지 모르겠음
        if(!Pattern.matches(nickname, nicknamePattern)){
            throw new Exception("닉네임 확인");
        }
        if(!Pattern.matches(password, passwordPattern)){
            throw new Exception("비밀번호 확인");
        }

        userService.addUser(userDTO);
    }

    // 로그인 (토큰 반환)
    @PostMapping("/api/login")
    public String userLogin(@RequestHeader(value = "jwt", required = false) String header, @RequestBody UserDTO userDTO) {
        /** 과제 요구사항 4
         *  로그인 한 사용자가 로그인 페이지 또는 회원가입 페이지에 접속한 경우
         * "이미 로그인이 되어있습니다."라는 메세지로 예외처리하기 */
        String token = "";

        // 추후에 AOP에 대해 공부한 후 AOP를 사용하여 예외처리 해볼 것
        if(header != null){
            String email = jwtProvider.getUserPk(header);
            System.out.println(email);
            return "이미 로그인 되어있습니다";
        }

        try {
            token = userService.loginUser(userDTO);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요");
        }


        return token;
    }

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
