package com.jjang051.board;

import com.jjang051.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardJpaApplicationTests {

    @Autowired
    private BoardRepository boardRepository;
    @Test
    void contextLoads() {
    }





}
