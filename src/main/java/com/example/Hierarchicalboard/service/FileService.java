package com.example.Hierarchicalboard.service;

import com.example.Hierarchicalboard.domain.Board;
import com.example.Hierarchicalboard.domain.File;
import com.example.Hierarchicalboard.dto.FileDto;
import com.example.Hierarchicalboard.repository.BoardRepository;
import com.example.Hierarchicalboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id,int boardId){
        File file = fileRepository.findById(id).get();
        Board board = boardRepository.findById(boardId).get();
        FileDto fileDto = FileDto.builder()
                .id(id)
                .board(board)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }

}
