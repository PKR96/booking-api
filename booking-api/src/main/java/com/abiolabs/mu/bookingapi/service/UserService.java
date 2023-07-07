package com.abiolabs.mu.bookingapi.service;

import com.abiolabs.mu.bookingapi.entity.User;
import com.abiolabs.mu.bookingapi.repository.UserRepository;
import com.abiolabs.mu.bookingapi.utils.JWTUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByEmail(@NonNull String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByUserName(@NonNull String userName){
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user){
           User newUser = User.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRoles())
                    .build();
           return userRepository.save(newUser);
    }
}
