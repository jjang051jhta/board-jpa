package com.jjang051.board.service;

import com.jjang051.board.dto.BoardDto;
import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public void insertBoard(BoardDto boardDto) {
        Board saveBoard = BoardDto.toEntity(boardDto);
        boardRepository.save(saveBoard);
    }

    public Board getBoard(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if(optionalBoard.isPresent()) {
            return optionalBoard.get();
        }
        throw new RuntimeException("해당하는 글이 삭제되었거나 없습니다.");
    }

    public void delete(Long id) {
        Board board = this.getBoard(id);
        boardRepository.delete(board);
    }

    public void deleteId(Long id) {
        //Board board = this.getBoard(id);
        boardRepository.deleteById(id);
    }

    public void modify(Board findBoard) {
        boardRepository.save(findBoard);
    }
}
