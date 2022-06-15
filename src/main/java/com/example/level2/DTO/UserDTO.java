package com.example.level2.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
//    private Long _id;
//    private String name;
    private String nickname;
    private String email;
    private String password;
}
