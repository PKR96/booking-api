package com.abiolabs.mu.bookingapi.service;

import com.abiolabs.mu.bookingapi.data.BookingData;
import com.abiolabs.mu.bookingapi.data.UserData;
import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.entity.enums.Status;
import com.abiolabs.mu.bookingapi.repository.BookingRepository;
import com.abiolabs.mu.bookingapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testGetListOfBookings() {
        Mockito.when(bookingRepository.findAllFromCurrentDateTime(Mockito.any(LocalDateTime.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(BookingData.getBooking())));
        List<Booking> bookingList = bookingService.getListOfBookings(0);
        Assertions.assertFalse(bookingList.isEmpty());
        Booking value = bookingList.stream().findFirst().orElse(null);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(Status.OPEN, value.getStatus());
        Assertions.assertEquals(LocalDate.now(), value.getDateTime().toLocalDate());
    }

    @Test
    void testSaveBooking() {
        Mockito.when(authentication.getName()).thenReturn("user11");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Mockito.when(userRepository.findByUserName(Mockito.any())).thenReturn(UserData.getDefaultUser());
        Mockito.when(bookingRepository.findByDateTime(Mockito.any(LocalDateTime.class))).thenReturn(BookingData.getBooking());
        Mockito.when(bookingRepository.save(Mockito.any(Booking.class))).thenReturn(null);
        bookingService.saveBooking(LocalDate.now().toString(), LocalTime.now().toString());
        Mockito.verify(bookingRepository, Mockito.times(1)).save(Mockito.any(Booking.class));
    }
}