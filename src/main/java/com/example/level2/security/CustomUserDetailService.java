package com.example.level2.security;

import com.example.level2.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* 토큰에 저장된 유저 정보를 활용해야 하기 때문에
CustomUserDetatilService 라는 이름의 클래스를 만들고
UserDetailsService를 상속받아 재정의 하는 과정 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          /*
          userRepository에서 UserDetails 타입을 return 할 수 없다. 따라서,
          1. User 클래스에 UserDetails를 구현하여 같은 클래스에서 관리하거나  <-- 이 방법 사용
          2. UserDetail를 구분하여 따로 구현하여야 한다
          */
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}