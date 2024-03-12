package com.example.Hierarchicalboard.service;

import com.example.Hierarchicalboard.domain.Board;
import com.example.Hierarchicalboard.domain.File;
import com.example.Hierarchicalboard.dto.FileDto;
import com.example.Hierarchicalboard.repository.BoardRepository;
import com.example.Hierarchicalboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<File> getFile(Board board){
        List<File> files = fileRepository.findByBoard(board);
        return files;
    }

}
