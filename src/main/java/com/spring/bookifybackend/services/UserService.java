package com.spring.bookifybackend.services;

import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.repoInterfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(){
        return this.userRepository.findAll();
    }




}
