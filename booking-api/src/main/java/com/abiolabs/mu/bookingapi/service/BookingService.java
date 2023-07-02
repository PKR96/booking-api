package com.abiolabs.mu.bookingapi.service;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.entity.enums.Status;
import com.abiolabs.mu.bookingapi.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getListOfBookings() {
        Pageable bookingSize = Pageable.ofSize(100);
        Page<Booking> bookingPage = this.bookingRepository.findAllFromCurrentDateTime(LocalDateTime.now(), bookingSize);
        return bookingPage.hasContent() ? bookingPage.getContent() : new ArrayList<>();
    }

    @Transactional
    public void saveBooking(LocalDateTime localDateTime){
        Booking booking = Booking.builder()
                .dateTime(localDateTime)
                .status(Status.CLOSED)
                .build();
        this.bookingRepository.save(booking);
    }
}
