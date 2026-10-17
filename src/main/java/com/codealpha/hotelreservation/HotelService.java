
package com.codealpha.hotelreservation;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public HotelService() {
       rooms.add(new Room(101, RoomCategory.STANDARD, 2000));
       rooms.add(new Room(102, RoomCategory.STANDARD, 2000));
       rooms.add(new Room(201, RoomCategory.DELUXE, 3500));
       rooms.add(new Room(202, RoomCategory.DELUXE, 3500));
       rooms.add(new Room(301, RoomCategory.SUITE, 5000));
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> result = new ArrayList<>();

        for (Room room : rooms) {
            if (room.isAvailable()
                    && (category == null || category.isBlank()
                    || room.getCategory().name()
                    .equalsIgnoreCase(category))) {
                result.add(room);
            }
        }

        return result;
    }

    public Reservation bookRoom(
            String guestName,
            int roomNumber,
            LocalDate checkIn,
            LocalDate checkOut) {

        if (guestName == null || guestName.isBlank()) {
            throw new IllegalArgumentException("Guest name is required.");
        }

        if (checkIn == null || checkOut == null
                || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException(
                    "Check-out must be after check-in.");
        }

        Room room = findRoom(roomNumber);

        if (room == null || !room.isAvailable()) {
            throw new IllegalArgumentException(
                    "Room is unavailable.");
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total = nights * room.getPricePerNight();

        Reservation reservation = new Reservation(
                guestName, roomNumber, checkIn, checkOut, total);

        reservations.add(reservation);
        room.setAvailable(false);

        return reservation;
    }

    public Reservation bookRoomByCategory(
        String guestName,
        String roomType,
        LocalDate checkIn,
        LocalDate checkOut) {

    if (roomType == null || roomType.isBlank()) {
        throw new IllegalArgumentException(
                "Please select a room type.");
    }

    RoomCategory category;

    try {
        category = RoomCategory.valueOf(
                roomType.trim().toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(
                "Invalid room type.");
    }

    for (Room room : rooms) {
        if (room.getCategory() == category
                && room.isAvailable()) {
            return bookRoom(
                    guestName,
                    room.getRoomNumber(),
                    checkIn,
                    checkOut);
        }
    }

    throw new IllegalArgumentException(
        "No rooms available for this category.");
}

    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public Reservation findReservation(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId()
                    .equalsIgnoreCase(reservationId)) {
                return reservation;
            }
        }
        return null;
    }
    public boolean cancelReservation(String reservationId) {
        Reservation reservation = findReservation(reservationId);

        if (reservation == null) {
            return false;
        }

        Room room = findRoom(reservation.getRoomNumber());

        if (room != null) {
            room.setAvailable(true);
        }

        reservations.remove(reservation);
        return true;
    }
}
