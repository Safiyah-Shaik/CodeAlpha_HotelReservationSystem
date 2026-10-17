
package com.codealpha.hotelreservation;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final List<Booking> bookings = new ArrayList<>();

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {

        LocalDate checkIn = LocalDate.parse(booking.getCheckIn());
        LocalDate checkOut = LocalDate.parse(booking.getCheckOut());

        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException(
                "Check-out must be after check-in."
            );
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

        double pricePerNight = switch (booking.getRoomType()) {
            case "Standard" -> 2000;
            case "Deluxe" -> 3500;
            case "Suite" -> 5000;
            default -> throw new IllegalArgumentException(
                "Invalid room type."
            );
        };

        booking.setTotalPrice(nights * pricePerNight);
        bookings.add(booking);

        return booking;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookings;
    }
    @PostMapping("/{index}/pay")
    public Booking payForBooking(@PathVariable int index) {

    if (index < 0 || index >= bookings.size()) {
        throw new IllegalArgumentException(
            "Booking not found."
        );
    }

        Booking booking = bookings.get(index);
        booking.setPaid(true);

        return booking;
    }

        @DeleteMapping("/{index}")
        public String cancelBooking(@PathVariable int index) {
            if (index < 0 || index >= bookings.size()) {
                throw new IllegalArgumentException("Booking not found.");
            }

        bookings.remove(index);
        return "Booking cancelled successfully.";
    }
}
