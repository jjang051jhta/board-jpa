package com.jjang051.board.repository;

import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import com.jjang051.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);

    @Query("select b from Board b " +
           "join fetch b.writer " +
           "where b.writer.userId = :userId"
    )
    List<Board> findAllBoards(@Param("userId") String userId);

    @Query("select c from Comment c " +
            "join fetch c.writer " +
            "where c.writer.userId = :userId")
    List<Comment> findAllComments(String userId);
}
