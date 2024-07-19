package com.jjang051.board.service;

import com.jjang051.board.dto.BoardDto;
import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public void modify(BoardDto findBoardDto, Long id) {
        //  findBoardDto.setId(999l);
        //  @Id 값을 가지고 같은지 다른지 판단한다. 이게 null이면 insert
        //  값은거면 save했을때  update를 날린다.
        //  findBoardDto.setId(93203902l);
        //  save()  둘다 한다   insert   update를 한다.
        //  이때 같은 객체를 판단하는 기준은 @Id를 가지고 한다.
        //  null이면 새로운 객체 즉 insert를하고
        //  니면 update
      boardRepository.save(BoardDto.toEntity(findBoardDto));
    }
}
