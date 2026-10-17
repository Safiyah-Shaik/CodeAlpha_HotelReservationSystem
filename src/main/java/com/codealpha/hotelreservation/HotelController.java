
package com.codealpha.hotelreservation;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms(
            @RequestParam(required = false) String category) {
        return hotelService.searchAvailableRooms(category);
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        return hotelService.getAllReservations();
    }


    public static class BookingResponse {
        private String guestName;
        private String roomType;
        private double totalPrice;

    public BookingResponse(String guestName, String roomType,
                           double totalPrice) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

    @GetMapping("/reservations/{id}")
    public Reservation getReservation(@PathVariable String id) {
        Reservation reservation =
                hotelService.findReservation(id);

        if (reservation == null) {
            throw new IllegalArgumentException(
                    "Reservation not found.");
        }

        return reservation;
    }


    @DeleteMapping("/reservations/{id}")
    public String cancelReservation(@PathVariable String id) {
        boolean cancelled = hotelService.cancelReservation(id);

        if (!cancelled) {
            throw new IllegalArgumentException(
                "Reservation not found.");
        }

        return "Reservation cancelled successfully.";
    }



    public static class BookingRequest {
    private String guestName;
    private String roomType;
    private String checkIn;
    private String checkOut;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
}
