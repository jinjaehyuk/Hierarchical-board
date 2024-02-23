package com.example.Hierarchicalboard.repository;

import com.example.Hierarchicalboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findByEmail(String email);
}
