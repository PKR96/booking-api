package com.abiolabs.mu.bookingapi.data;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.entity.enums.Status;

import java.time.LocalDateTime;

public class BookingData {

    public static Booking getBooking(){
        return Booking.builder()
                .id(1)
                .status(Status.OPEN)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
