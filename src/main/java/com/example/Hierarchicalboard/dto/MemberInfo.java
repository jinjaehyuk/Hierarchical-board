package com.example.Hierarchicalboard.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfo {
    private int memberNo;
    private String email;
    private String name;

    public MemberInfo(int memberNo, String email, String name) {
        this.memberNo = memberNo;
        this.email = email;
        this.name = name;
    }
}
