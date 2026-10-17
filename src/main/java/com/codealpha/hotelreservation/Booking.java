
package com.codealpha.hotelreservation;

public class Booking {

    private String guestName;
    private String roomType;
    private String checkIn;
    private String checkOut;
    private Double totalPrice;
    private boolean paid = false;

    public Booking() {
    }

    public Booking(String guestName, String roomType,
                   String checkIn, String checkOut,
                   Double totalPrice) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
