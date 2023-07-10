package com.abiolabs.mu.bookingapi.service;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.entity.User;
import com.abiolabs.mu.bookingapi.entity.enums.Status;
import com.abiolabs.mu.bookingapi.repository.BookingRepository;
import com.abiolabs.mu.bookingapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public List<Booking> getListOfBookings(int page) {
        Pageable bookingSize = PageRequest.of(page,100);
        Page<Booking> bookingPage = this.bookingRepository.findAllFromCurrentDateTime(LocalDateTime.now(), bookingSize);
        return bookingPage.hasContent() ? bookingPage.getContent() : new ArrayList<>();
    }

    @Transactional
    public void saveBooking(String date, String time){
        String dateTimeString = date + " " + time+ ":00.000";
        String userName =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(userName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        Booking booking = bookingRepository.findByDateTime(dateTime);
        booking.setStatus(Status.CLOSED);
        booking.setUser(user);
        this.bookingRepository.save(booking);
    }
}
