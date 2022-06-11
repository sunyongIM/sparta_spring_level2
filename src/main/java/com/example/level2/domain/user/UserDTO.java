package com.example.level2.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    private final Long _id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String password;
}
