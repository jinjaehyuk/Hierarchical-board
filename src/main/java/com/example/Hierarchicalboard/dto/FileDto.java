package com.example.Hierarchicalboard.dto;

import com.example.Hierarchicalboard.domain.Board;
import com.example.Hierarchicalboard.domain.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
    private Long id;
    private Board board;
    private String origFilename;
    private String filename;
    private String filePath;

    public File toEntity(){
        File build = File.builder()
                .id(id)
                .board(board)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, Board board, String origFilename, String filename, String filePath) {
        this.id = id;
        this.board = board;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
