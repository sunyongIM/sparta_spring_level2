package com.example.level2.service;

import com.example.level2.domain.user.User;
import com.example.level2.DTO.UserDTO;
import com.example.level2.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    // 회원 가입
    @Transactional
    public void addUser(UserDTO userDTO){
        User user = new User(userDTO);
        userRepository.save(user);
    }

//    // 로그인
//    public void loginUser(UserDTO userDTO){
//
//    }

    // 로그인
    public Boolean loginUser(String email, String pwd){
        System.out.println(email+pwd);
        return userRepository.findByEmailAndPassword(email,pwd);
    }

    // 마이페이지
    public User detailsUser(Long _id){
        return userRepository.findById(_id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
    }
    
    // 회원정보 수정

}
