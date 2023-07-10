package com.abiolabs.mu.bookingapi.controller;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{page}")
    @ResponseBody
    public ResponseEntity<List<Booking>> retrieveAvailableBookingSlots(@PathVariable int page) {
        return new ResponseEntity<>(this.bookingService.getListOfBookings(page), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{selectedDate}/{selectedTime}")
    public ResponseEntity<Void> saveBooking(@PathVariable String selectedDate, @PathVariable String selectedTime) {
        this.bookingService.saveBooking(selectedDate, selectedTime);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}


