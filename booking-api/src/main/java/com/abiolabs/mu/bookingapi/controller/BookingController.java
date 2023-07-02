package com.abiolabs.mu.bookingapi.controller;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Booking>> retrieveAvailableBookingSlots() {
        return new ResponseEntity<>(this.bookingService.getListOfBookings(), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Void> saveBooking(@RequestBody LocalDateTime dateTimeBooked) {
        this.bookingService.saveBooking(dateTimeBooked);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}


