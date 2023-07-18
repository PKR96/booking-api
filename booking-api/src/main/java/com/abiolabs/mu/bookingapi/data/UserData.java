package com.abiolabs.mu.bookingapi.data;

import com.abiolabs.mu.bookingapi.entity.Role;
import com.abiolabs.mu.bookingapi.entity.User;
import com.abiolabs.mu.bookingapi.entity.enums.RoleType;

import java.util.Collections;

public class UserData {

    public static User getDefaultUser() {
        Role role = Role.builder()
                .id(1L)
                .roleType(RoleType.GUEST)
                .build();
        return User.builder()
                .userId(1L)
                .userName("Pawan")
                .password("12345")
                .roles(Collections.singleton(role))
                .build();
    }
}
