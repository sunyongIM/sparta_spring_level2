package com.example.level2.domain.user;

import com.example.level2.DTO.UserDTO;
import com.example.level2.domain.Timestamped;
import com.example.level2.domain.board.Board;
import com.example.level2.domain.like.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "USER")
@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

//    @Column(nullable = false)
//    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "writerId", cascade = CascadeType.ALL)
    private List<Board> boards;

    @OneToMany(mappedBy = "likeId", cascade = CascadeType.ALL)
    private List<Like> likes;

    public User(Long _id, String email, String nickname, String password, List<Board> boards, List<Like> likes){
        this._id = _id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.boards = boards;
        this.likes = likes;
    }

    public User(UserDTO userDTO) {
//        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.nickname = userDTO.getNickname();
        this.password = userDTO.getPassword();
    }


    public void update(UserDTO userDTO) {
//        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.nickname = userDTO.getNickname();
        this.password = userDTO.getPassword();
    }


    /* CustomUserDetailService 에서 기술한,
    User 클래스에 UserDetails를 구현하여 같은 클래스에서 관리하는 방법 */
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // 로그인 id를 email로 구현 할 것이기에 getUsername을 재정의 해준다
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
