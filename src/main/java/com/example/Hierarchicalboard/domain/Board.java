package com.example.Hierarchicalboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity //Database Table과 매핑하는 객체.
//@Table(name="user3") //Database Table 이름이 user3와 User라는 객체가 매핑.
@NoArgsConstructor // 기본 생성자가 필요
@Setter
@Getter
public class Board {

    @Id //이 필드가 Table의 PK
    @Column(name="board_id") //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    private int p_id;

    private int depth;

    @Column(length = 100)
    private String Title;

    @Lob // 대용량 텍스트type
    private String content;

    private int viewCnt;

    @CreationTimestamp //현재시간이 저장될 때 자동으로 생성.
    private LocalDateTime regdate;

    @ManyToOne(fetch = FetchType.LAZY) //게시물 N --- 1, FetchType.LAZY 무조건 데이터를 가지고 와라.
    @JoinColumn(name = "member_no")
    private Member member;


}
