package com.jjang051.board;


import com.jjang051.board.entity.Team;
import com.jjang051.board.entity.User;
import com.jjang051.board.repository.TeamRepository;
import com.jjang051.board.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {


    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void memberLazy() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        userRepository.save(new User("member1", teamA));
        userRepository.save(new User("member2", teamB));

        List<User> members = userRepository.findAll();
        //then
        for (User member : members) {
            member.getTeam().getName();
        }
    }
}
