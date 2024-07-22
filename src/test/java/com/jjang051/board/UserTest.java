package com.jjang051.board;

import com.jjang051.board.entity.Team;
import com.jjang051.board.repository.TeamRepository;
import com.jjang051.board.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void memberLazy() {
        Team team01 = new Team("team01");
    }
}
