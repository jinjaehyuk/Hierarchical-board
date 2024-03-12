package com.example.Hierarchicalboard.repository;

import com.example.Hierarchicalboard.domain.Board;
import com.example.Hierarchicalboard.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByBoard(Board board);
}
