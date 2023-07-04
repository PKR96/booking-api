package com.abiolabs.mu.bookingapi.entity;

import com.abiolabs.mu.bookingapi.entity.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "ROLE_TYPE", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
