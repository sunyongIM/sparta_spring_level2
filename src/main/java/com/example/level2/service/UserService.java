package com.example.level2.service;

import com.example.level2.domain.user.User;
import com.example.level2.DTO.UserDTO;
import com.example.level2.domain.user.UserRepository;
import com.example.level2.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    // 회원 가입
    @Transactional
    public void addUser(UserDTO userDTO){
        User newUser = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .nickname(userDTO.getNickname())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build();
        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e){
            System.out.println("이미 가입한 이메일 입니다");
        }
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
    public UserDTO detailsUser(String email){
        User member = userRepository.findByEmail(email).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        return UserDTO.toUser(member);
    }
    
    // 회원정보 수정
    @Transactional
    public UserDTO alterUser(String email, UserDTO userDTO){
        User member = userRepository.findByEmail(email).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        member.update(userDTO);

        return UserDTO.toUser(member);
    }
}
