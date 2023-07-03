package com.abiolabs.mu.bookingapi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long userId;

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
    })
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    @Transient
    private String token;
}
