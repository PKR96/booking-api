package com.abiolabs.mu.bookingapi.data;

import com.abiolabs.mu.bookingapi.entity.Role;
import com.abiolabs.mu.bookingapi.entity.enums.RoleType;

import java.util.HashSet;

public class RoleData {

    public static Role getRole(){
        return Role.builder()
                .id(1L)
                .roleType(RoleType.GUEST)
                .users(new HashSet<>())
                .build();
    }
}
