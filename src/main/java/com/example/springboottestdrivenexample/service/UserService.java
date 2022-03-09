package com.example.springboottestdrivenexample.service;

import com.example.springboottestdrivenexample.exception.NotFoundException;
import com.example.springboottestdrivenexample.model.User;
import com.example.springboottestdrivenexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        throw new NotFoundException();
    }

}
