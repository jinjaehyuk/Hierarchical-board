package com.example.Hierarchicalboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @Column(name="member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNo;

    @Column(length = 255)
    private String email;

    @Column(length = 30)
    private String name;

    @Column(length=255)
    private String password;

    @CreationTimestamp
    private LocalDateTime regdate;


}
