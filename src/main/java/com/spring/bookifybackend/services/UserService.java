package com.spring.bookifybackend.services;

import com.spring.bookifybackend.entities.User;
import com.spring.bookifybackend.exceptions.UserNotFoundException;
import com.spring.bookifybackend.repoInterfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
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

    public void delete(User user){
        userRepository.delete(user);
    }

    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(String email){
        User user = userRepository.getUserByEmail(email);
        return user == null;
    }

    public User get(Long id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw new UserNotFoundException("Couldn't find any user with ID = "+id);
        }
    }

    public void updateUserEnabledStatus(Long id , boolean enabled){
        userRepository.updateEnabledStatus(id,enabled);
    }

}
