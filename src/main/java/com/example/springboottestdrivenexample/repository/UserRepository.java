package com.example.springboottestdrivenexample.repository;

import com.example.springboottestdrivenexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findById(String id);
}
