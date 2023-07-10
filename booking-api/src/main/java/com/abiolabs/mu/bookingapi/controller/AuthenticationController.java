package com.abiolabs.mu.bookingapi.controller;

import com.abiolabs.mu.bookingapi.entity.User;
import com.abiolabs.mu.bookingapi.service.UserService;
import com.abiolabs.mu.bookingapi.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
@Validated
@Slf4j
public class AuthenticationController {

    private final UserService userService;

    private final JWTUtils jwtUtils;

    @Autowired
    public AuthenticationController(UserService userService, JWTUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<Object> signUp(@RequestBody @Valid User user) {
        Map<String, String> messageMap = new HashMap<>();
        ResponseEntity<Object> responseEntity = getResponseEntityForExistingUser(messageMap, user);

        if (responseEntity != null) {
            return responseEntity;
        }
        return ResponseEntity.ok()
                .headers(getHttpHeadersWithJWT(user))
                .body(userService.saveUser(user));
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody @Valid User user) {
        User dbUser = userService.getUserByUserName(user.getUserName());
        Map<String, String> messageMap = new HashMap<>();
        if (dbUser == null) {
            messageMap.put("error message", "Invalid username");
            return new ResponseEntity<>(messageMap, HttpStatus.UNAUTHORIZED);
        }
        if (!BCrypt.checkpw(user.getPassword(), dbUser.getPassword())) {
            messageMap.put("error message", "Invalid password");
            return new ResponseEntity<>(messageMap, HttpStatus.UNAUTHORIZED);
        }
        HttpHeaders httpHeaders = getHttpHeadersWithJWT(user);
        return ResponseEntity.ok().headers(httpHeaders).body(user);
    }

    private ResponseEntity<Object> getResponseEntityForExistingUser(Map<String, String> messageMap, User user) {
        User existingUserWithEmail = this.userService.getUserByEmail(user.getEmail());
        User existingUserWithUsername = this.userService.getUserByUserName(user.getUserName());

        if (existingUserWithEmail != null) {
            messageMap.put("error message", "A user with this email address exists, kindly login");
            return new ResponseEntity<>(messageMap, HttpStatus.BAD_REQUEST);

        }
        if (existingUserWithUsername != null) {
            messageMap.put("error message", "A user with this username exists, kindly login");
            return new ResponseEntity<>(messageMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private HttpHeaders getHttpHeadersWithJWT(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Access-Control-Expose-Headers", "Authorization");
        httpHeaders.set("Authorization", this.jwtUtils.generateJWT(user.getUserName(), user.getRoles()));
        return httpHeaders;
    }
}
