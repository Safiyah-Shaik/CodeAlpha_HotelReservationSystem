
package com.codealpha.hotelreservation;

public class Room {
    private int roomNumber;
    private RoomCategory category;
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, RoomCategory category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
