package com.jjang051.board.repository;


import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b left join fetch b.comments")
    List<Comment> findByBoardFetch();

//    @Query(value=
//            "select b from Board b where b.title like % :keyword % "
//            )
//    List<Comment> findBySubject(@Param("keyword") String keyword);
//
//    @Query(value=
//            "select * from Board b where b.title like % :keyword % ",
//            nativeQuery = true
//    )
//    List<Comment> findBySubject02(@Param("keyword") String keyword);
}
