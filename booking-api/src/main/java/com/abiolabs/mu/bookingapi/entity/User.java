package com.abiolabs.mu.bookingapi.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false)
    private Long userId;

    @Column(name = "USERNAME", unique = true, nullable = false)
    @NotNull
    private String userName;

    @NotNull
    @Column(name = "EMAIL", unique = true, nullable = false)
    @Email(message = "User has not entered a valid email address")
    private String email;

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USERS_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLES_ID"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();
}
