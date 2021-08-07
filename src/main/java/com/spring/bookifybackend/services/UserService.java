package com.spring.bookifybackend.services;

import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.repoInterfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll(){
        return this.userRepository.findAll();
    }

    public void save(User user){
        encodePassword(user);
        userRepository.save(user);
    }

    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(String email){
        User user = userRepository.getUserByEmail(email);
        return user == null;
    }

}
