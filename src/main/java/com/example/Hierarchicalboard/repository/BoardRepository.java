package com.example.Hierarchicalboard.repository;

import com.example.Hierarchicalboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board,Integer> {

    @Query(value = "WITH RECURSIVE CTS AS (\r\n"+
            "                SELECT  board_id\r\n"+
            "                       ,title\r\n"+
            "                       ,content\r\n"+
            "                       ,p_id\r\n"+
            "                       ,depth\r\n"+
            "                       ,member_no\r\n"+
            "					   ,regdate\r\n"+
            "					   ,view_cnt\r\n"+
            "                       ,CAST(board_id as CHAR(255)) lvl\r\n"+
            "                       ,board_id as groupno\r\n"+
            "                FROM board\r\n"+
            "                where p_id = 0\r\n"+
            "                UNION ALL\r\n"+
            "                SELECT  b.board_id\r\n"+
            "           				,CONCAT(REPEAT('    ', b.depth), 'ㄴ', b.title) as title\r\n"+
            "                       ,b.content\r\n"+
            "                       ,b.p_id\r\n"+
            "                       ,b.depth\r\n"+
            "                       ,b.member_no\r\n"+
            "                       ,b.regdate\r\n"+
            "                       ,b.view_cnt\r\n"+
            "                       ,CONCAT(c.lvl, ',', b.board_id) lvl\r\n"+
            "                       ,substring_index(c.lvl, ',',1) as groupno\r\n"+
            "                FROM board b\r\n"+
            "                INNER JOIN CTS c\r\n"+
            "                ON b.p_id = c.board_id\r\n"+
            "            )\r\n"+
            "            SELECT b.board_id\r\n"+
            "                  ,title\r\n"+
            "                  ,content\r\n"+
            "                  ,p_id\r\n"+
            "                  ,depth\r\n"+
            "                  ,member_no\r\n"+
            "                  ,b.regdate\r\n"+
            "                  ,b.view_cnt\r\n"+
            "                  ,lvl\r\n"+
            "                  ,groupno\r\n"+
            "            from cts as b\r\n"+
            "            ORDER BY groupno desc, lvl",
    countQuery = " select count(*) from board",
    nativeQuery = true )
    Page<Board> findAllBoard(Pageable pageable);

    @Query(value = "select count(b) from Board b")
    long getBoardCount();
}
