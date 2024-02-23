package com.example.Hierarchicalboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private String name;
    private String email;
    private String password;
    private String confirmpassword;
}
