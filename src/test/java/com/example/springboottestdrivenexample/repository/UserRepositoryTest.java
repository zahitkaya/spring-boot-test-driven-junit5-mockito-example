package com.example.springboottestdrivenexample.repository;

import com.example.springboottestdrivenexample.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void find_by_id(){
        Optional<User> user = userRepository.findById("1");
        assertTrue(user.isPresent());
    }

    @Test
    public void when_not_found_test_find_by_id(){
        Optional<User> user = userRepository.findById("5");
        assertFalse(user.isPresent());
    }

    @Test
    void after_saved_find_by_id() {
        userRepository.save(User.builder().id("1").name("z").surname("k").build());
        assertTrue(userRepository.findById("1").isPresent());
    }
}