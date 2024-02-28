package com.example.Hierarchicalboard.repository;

import com.example.Hierarchicalboard.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
