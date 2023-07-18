package com.abiolabs.mu.bookingapi.repository;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.entity.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BookingRepositoryTests {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testBookingFindFirstByOrderByDateTimeDesc() {
        Booking booking = bookingRepository.findFirstByOrderByDateTimeDesc();
        assertNotNull(booking);
        assertEquals(Status.OPEN, booking.getStatus());
        assertEquals(LocalDate.of(2023, 9, 29), booking.getDateTime().toLocalDate());
    }

    @Test
    void testFindAllFromCurrentDateTime() {
        Page<Booking> bookingPage = bookingRepository.findAllFromCurrentDateTime(LocalDateTime.now(), Pageable.ofSize(100));
        assertNotNull(bookingPage.get());
        assertTrue(bookingPage.get().findAny().isPresent());
    }

    @Test
    void testFindByDateTime(){
        Booking booking1 = bookingRepository.findFirstByOrderByDateTimeDesc();
        Booking booking2 = bookingRepository.findByDateTime(booking1.getDateTime());
        assertNotNull(booking2);
        assertEquals(Status.OPEN, booking2.getStatus());
        assertEquals(LocalDate.of(2023, 9, 29), booking2.getDateTime().toLocalDate());
    }
}
