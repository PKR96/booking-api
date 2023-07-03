package com.abiolabs.mu.bookingapi.controller;

import com.abiolabs.mu.bookingapi.entity.Role;
import com.abiolabs.mu.bookingapi.entity.User;
import com.abiolabs.mu.bookingapi.service.UserService;
import com.abiolabs.mu.bookingapi.utils.JWTUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping(value = "auth")
@Validated
@Slf4j
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestBody @Valid User user){
     Map<String,String> messageMap = new HashMap<>();
     ResponseEntity<?> responseEntity = getResponseEntityForExistingUser(messageMap,user);

     if(responseEntity != null){
         return responseEntity;
     }
     return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseEntityForExistingUser(Map<String,String> messageMap, User user){
        User existingUserWithEmail = this.userService.getUserByEmail(user.getEmail());
        User existingUserWithUsername = this.userService.getUserByUserName(user.getUserName());

        if(existingUserWithEmail != null) {
            messageMap.put("error message","A user with this email address exists, kindly login");
            return new ResponseEntity<>(messageMap,HttpStatus.BAD_REQUEST);

        }
        if(existingUserWithUsername != null) {
            messageMap.put("error message","A user with this username exists, kindly login");
            return new ResponseEntity<>(messageMap,HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
