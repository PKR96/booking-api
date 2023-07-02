package com.abiolabs.mu.bookingapi.scheduledbooking;

import com.abiolabs.mu.bookingapi.entity.Booking;
import com.abiolabs.mu.bookingapi.entity.enums.Status;
import com.abiolabs.mu.bookingapi.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class BookingCronJob {

    private final BookingRepository bookingRepository;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private final Predicate<LocalDateTime> isWeekend = dateTime -> dateTime.getDayOfWeek() == DayOfWeek.SATURDAY || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY;

    @Autowired
    public BookingCronJob(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(cron = "0 0 0 L * ?")
    @Transactional
    public void generateBookingEntries() {
        List<Booking> bookingsToSave = new ArrayList<>();
        this.setLatestLocalDateTimeIfDataExists();

        while (startDate.isBefore(endDate)) {
            if (!isWeekend.test(startDate)) {
                Booking booking = createBooking(startDate);
                bookingsToSave.add(booking);
            }
            startDate = getNextTimeSlot(startDate);
        }
        try {
            bookingRepository.saveAll(bookingsToSave);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            log.error("An attempt to save existing bookings in the database was done: {}", dataIntegrityViolationException.getMessage());
        }
    }

    private Booking createBooking(LocalDateTime startTime) {
        Booking booking = new Booking();
        booking.setDateTime(startTime);
        booking.setStatus(Status.OPEN);
        return booking;
    }

    private LocalDateTime getNextTimeSlot(LocalDateTime dateTime) {
        LocalDateTime nextTimeSlot = dateTime.plusMinutes(20);

        if (nextTimeSlot.getHour() >= 17) {
            return nextTimeSlot.plusDays(1).withHour(8).withMinute(0).withSecond(0);
        }

        return nextTimeSlot;
    }

    private void setLatestLocalDateTimeIfDataExists() {
        Booking booking = bookingRepository.findFirstByOrderByDateTimeDesc();
        LocalDateTime startDate;
        LocalDateTime endDate;
        if (booking != null) {
            startDate = booking.getDateTime().plusDays(1).withHour(8).withMinute(0).withSecond(0);
            endDate = booking.getDateTime().plusMonths(1).withDayOfMonth(1).minusDays(1).withHour(17).withMinute(0).withSecond(0);
        } else {
            startDate = LocalDateTime.now().plusMonths(1).withDayOfMonth(1).withHour(8).withMinute(0).withSecond(0);
            endDate = startDate.plusMonths(3).withDayOfMonth(1).minusDays(1).withHour(17).withMinute(0).withSecond(0);
        }
        this.setStartAndEndDate(startDate, endDate);
    }

    private void setStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
