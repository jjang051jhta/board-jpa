package com.jjang051.board.service;

import com.jjang051.board.dto.BoardDto;
import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.*;
import com.jjang051.board.repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.jjang051.board.entity.QBoard.*;
import static com.jjang051.board.entity.QComment.comment;
import static com.jjang051.board.entity.QMember.member;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final JPAQueryFactory queryFactory;

    @Value("${pagination.size}")  // "3"
    private int size;
    public List<Board> getQueryDslList() {



        QMember writer = new QMember("writer");
        QMember commentWriter = new QMember("commentWriter");
        List<Board> boardList =
                queryFactory
                .selectFrom(board)
                .leftJoin(board.writer, writer)
                .fetchJoin()
                .leftJoin(board.comments, comment)
                .fetchJoin()
                .leftJoin(comment.writer, commentWriter)
                .fetchJoin()
                .fetch();

        return boardList;
    }

    public Board getQuerydslBoard(Long id) {

        QMember writer = new QMember("writer");
        QMember commentWriter = new QMember("commentWriter");
        return queryFactory
                .selectFrom(board)
                .leftJoin(board.writer, writer)
                .fetchJoin()
                .leftJoin(board.comments, comment)
                .fetchJoin()
                .leftJoin(comment.writer, commentWriter)
                .fetchJoin()
                .where(board.id.eq(id))
                .fetchOne();
    }



    public List<Board> getBoardList() {
        //return boardRepository.findAll();
        //return boardRepository.findByBoardFetch();
        return boardRepository.findAllFetchBoard();
    }

    public void insertBoard(BoardDto boardDto) {
        Board saveBoard = BoardDto.toEntity(boardDto);
        boardRepository.save(saveBoard);
    }

    public Board getBoard(Long id) {
        //Optional<Board> optionalBoard = boardRepository.findById(id);
        Optional<Board> optionalBoard = boardRepository.findByFetchMemberAndCommentId(id);
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

    public Page<Board> getSearchResultList(String keyword, String category, int page) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(category.equals("title")) {
            booleanBuilder.or(board.title.contains(keyword));
        }
        if(category.equals("writer")) {
            booleanBuilder.or(board.writer.userName.contains(keyword));
        }
        if(category.equals("content")) {
            booleanBuilder.or(board.content.contains(keyword));
        }
        if(category.equals("all")) {
            booleanBuilder
                    .or(board.title.contains(keyword))
                    .or(board.writer.userName.contains(keyword))
                    .or(board.content.contains(keyword));
        }
        //최적화
        //select 고정
        //검색어 고정
        QMember writer = new QMember("writer");
        QMember commentWriter = new QMember("commentWriter");
        // lazy  상태의 연관관계 오브젝트들을 한번에 불러와라(처음 부를떄  쿼리나가는 걸 막기위해
        // 프록시객체(가짜)를 만들어서 가지고 있음 writer의 속성 또는 메서드 호출할때 이때 쿼리가 나간다.)
        // eager (즉시로딩 관련된 쿼리가 나가게 된다.  board writer)  성능저하
        Pageable pageable = PageRequest.of(page,size);
        List<Board> boardList =
                queryFactory
                .selectFrom(board)
                .leftJoin(board.writer, writer)
                .fetchJoin()
                .leftJoin(board.comments, comment)
                .fetchJoin()
                .leftJoin(comment.writer, commentWriter)
                .fetchJoin()
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total =
                queryFactory
                .select(board.count())
                .from(board)
                .fetchOne();
        //querydsl 리컨타입에 Page<> 없다
        // PageImpl을 통해서 값을 전달하면 된다.
        return new PageImpl<>(boardList,pageable,total);
        //return boardList;
    }


}
