package com.example.Hierarchicalboard.service;

import com.example.Hierarchicalboard.domain.Member;
import com.example.Hierarchicalboard.dto.MemberDto;
import com.example.Hierarchicalboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member addMember(MemberDto memberDto) {
        Member member= new Member();
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        member.setPassword(memberDto.getPassword());
        member = memberRepository.save(member);
        return member;
    }

    @Transactional
    public Member getMember(String email) {
        return memberRepository.findByEmail(email);
    }
}
