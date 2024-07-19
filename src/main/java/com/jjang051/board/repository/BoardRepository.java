package com.jjang051.board.repository;


import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b left join fetch b.comments")
    List<Comment> findByBoardFetch();



}
