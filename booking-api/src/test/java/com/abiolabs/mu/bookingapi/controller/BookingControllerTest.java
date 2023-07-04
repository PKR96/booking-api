package com.abiolabs.mu.bookingapi.controller;

import com.abiolabs.mu.bookingapi.entity.Role;
import com.abiolabs.mu.bookingapi.entity.User;
import com.abiolabs.mu.bookingapi.entity.enums.RoleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;


class BookingControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    void testRetrieveAvailableBookingSlots() throws Exception {
//        mockMvc.perform(get("/bookings/{page}", 100)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isAccepted());
//    }

    @Test
    void generateJson() throws JsonProcessingException {
        Role role = Role.builder().roleType(RoleType.GUEST)
                .build();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = User.builder()
                .userName("PKR")
                .email("pawanramkissoon@yahoo.com")
                .password("admin")
                .roles(roles)
                .build();

        System.out.println(new ObjectMapper().writeValueAsString(user));
    }
}
