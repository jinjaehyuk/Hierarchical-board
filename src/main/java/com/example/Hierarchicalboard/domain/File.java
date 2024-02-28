package com.example.Hierarchicalboard.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class File {

        /*
        id,board_id, original_name, save_name, size, delete_yn, create_date, delete_date
         */
        @Id //이 필드가 Table의 PK
        @Column(name="id") //
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name="board_id")
        private Board board;

        @Column(nullable = false)
        private String origFilename;

        @Column(nullable = false)
        private String filename;

        @Column(nullable = false)
        private String filePath;

        @Builder
        public File(Long id, Board board, String origFilename, String filename, String filePath) {
            this.id = id;
            this.board = board;
            this.origFilename = origFilename;
            this.filename = filename;
            this.filePath = filePath;
        }

}
