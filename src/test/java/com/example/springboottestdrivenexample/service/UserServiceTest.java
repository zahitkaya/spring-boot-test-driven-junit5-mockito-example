package com.example.springboottestdrivenexample.service;

import com.example.springboottestdrivenexample.exception.NotFoundException;
import com.example.springboottestdrivenexample.model.User;
import com.example.springboottestdrivenexample.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void getUserById() {
        given(userRepository.findById(Mockito.anyString())).willReturn(Optional.of(User.builder().id("1").name("name").surname("surname").build()));

        User user = userService.getUserById("5");

        assertNotNull(user);
        assertEquals("1",user.getId());
        assertEquals("name",user.getName());
        assertEquals("surname",user.getSurname());
    }

    @Test
    void user_not_found_test(){
        given(userRepository.findById(Mockito.anyString())).willThrow(new NotFoundException());

        assertThrows(NotFoundException.class , () -> userService.getUserById("2"));
    }
}