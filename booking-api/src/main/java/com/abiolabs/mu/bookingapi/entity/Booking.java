package com.abiolabs.mu.bookingapi.entity;

import com.abiolabs.mu.bookingapi.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "DATE_TIME",unique = true,nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "STATUS",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
