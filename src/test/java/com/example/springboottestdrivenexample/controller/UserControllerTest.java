package com.example.springboottestdrivenexample.controller;

import com.example.springboottestdrivenexample.exception.NotFoundException;
import com.example.springboottestdrivenexample.model.User;
import com.example.springboottestdrivenexample.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class) // it's inject @AutoConfigureMockMvc automatically
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(status().isOk());
    }

    @Test
    public void getUserById() throws Exception {
        given(userService.getUserById(Mockito.anyString())).willReturn(User.builder().id("test id").name("test user").surname("testt").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("name").value("test user"))
                .andExpect(jsonPath("surname").value("testt"))
                .andExpect(jsonPath("id").value("test id"));

    }

    @Test
    public void user_not_found_exception() throws Exception{

        given(userService.getUserById(Mockito.anyString())).willThrow(new NotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/users/5")).andExpect(status().isNotFound());

    }
}
