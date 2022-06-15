package com.example.level2.service;

import com.example.level2.domain.user.User;
import com.example.level2.DTO.UserDTO;
import com.example.level2.domain.user.UserRepository;
import com.example.level2.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 회원 가입
    @Transactional
    public void addUser(UserDTO userDTO){
        User newUser = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .nickname(userDTO.getNickname())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build();
        userRepository.save(newUser);
    }

    // 로그인
    public String loginUser(UserDTO userDTO){
        User member = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if(!passwordEncoder.matches(userDTO.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtProvider.createToken(member.getUsername(), member.getRoles());
    }


    // 마이페이지
    public User detailsUser(Long _id){
        return userRepository.findById(_id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
    }
    
    // 회원정보 수정

}
