package com.example.Hierarchicalboard.service;

import com.example.Hierarchicalboard.domain.Board;
import com.example.Hierarchicalboard.domain.Member;
import com.example.Hierarchicalboard.repository.BoardRepository;
import com.example.Hierarchicalboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Board addBoard(int memberNo, String title, String content) {
        Member member = memberRepository.findById(memberNo).orElseThrow();
        Board board = new Board();
        board.setDepth(0);
        board.setTitle(title);
        board.setContent(content);
        board.setP_id(0);
        board.setViewCnt(0);
        board.setRegdate(LocalDateTime.now());
        board.setMember(member);
        boardRepository.save(board);
        return board;
    }

    @Transactional(readOnly = true)
    public long getTotalCount() {
        return boardRepository.getBoardCount();
    }
    @Transactional(readOnly = true)
    public List<Board> showAll(int page){
        Pageable pageable = PageRequest.of(page,10);
        return boardRepository.findAllBoard(pageable).getContent();
    }
}
