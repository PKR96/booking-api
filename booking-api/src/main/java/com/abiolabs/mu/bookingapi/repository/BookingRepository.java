package com.abiolabs.mu.bookingapi.repository;

import com.abiolabs.mu.bookingapi.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findFirstByOrderByDateTimeDesc();

    @Query("SELECT b FROM Booking b WHERE b.dateTime >= :currentDateTime and b.status = 'OPEN' ORDER BY b.dateTime ASC")
    Page<Booking> findAllFromCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable);

    Booking findByDateTime(LocalDateTime localDateTime);
}
