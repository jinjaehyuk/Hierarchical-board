package com.example.Hierarchicalboard.repository;

import com.example.Hierarchicalboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {

}
