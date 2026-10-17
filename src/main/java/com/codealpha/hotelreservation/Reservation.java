
package com.codealpha.hotelreservation;

import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private String reservationId;
    private String guestName;
    private int roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalAmount;
    private boolean paid;

    public Reservation(String guestName, int roomNumber,
                       LocalDate checkIn, LocalDate checkOut,
                       double totalAmount) {
        this.reservationId = UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase();
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
        this.paid = false;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
