package com.example.blogger.users;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(showSql = true) //Every test run in seprate DB
// @SpringBootTest  // to run all test on same db we have provide config or seprate db like test
@ActiveProfiles("test")
public class UserRepositoryTests {
    @Autowired private UsersRepository usersRepository;
    @Test
    @Order(1)
    void can_create_users(){
        usersRepository.save(UserEntity.builder().username("kakarot").email("kakarot@email.com").build());
    }

    @Test
    @Order(2)
    void can_find_users_by_username(){
        usersRepository.save(UserEntity.builder().username("kakarot").email("kakarot@email.com").build());
        var user=usersRepository.findByUsername("kakarot").orElseThrow(()->new RuntimeException("user not found"));
        assertEquals(user.getUsername(),"kakarot");
    }
}
