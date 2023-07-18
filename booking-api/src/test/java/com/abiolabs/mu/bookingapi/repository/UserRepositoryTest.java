package com.abiolabs.mu.bookingapi.repository;

import com.abiolabs.mu.bookingapi.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail(){
        User user = userRepository.findByEmail("user11@example.com");
        assertNotNull(user);
        assertEquals("user11",user.getUserName());
    }

    @Test
    void testFindByUsername(){
        User user = userRepository.findByUserName("user11");
        assertNotNull(user);
        assertEquals("user11@example.com",user.getEmail());
    }
}
