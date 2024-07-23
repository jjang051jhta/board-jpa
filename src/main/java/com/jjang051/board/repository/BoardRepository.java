package com.jjang051.board.repository;


import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {



    //"select * from board"
    //@Query("select b from Board b left join fetch b.writer m left join fetch b.comments c left join fetch c.member cm")
    @Query("select b from Board b " +
            "left join fetch b.writer " +
            "left join fetch b.comments c " +
            "left join fetch c.writer")

    List<Board> findAllFetchBoard();


    //querydsl

    @Query("select b from Board b " +
            "left join fetch b.writer " +
            "left join fetch b.comments c " +
            "left join fetch c.writer "+
            "where b.id = :id"
    )
    Optional<Board> findByFetchMemberAndCommentId(@Param("id") Long id);
    //n + 1 문제 풀때 join fetch


}
