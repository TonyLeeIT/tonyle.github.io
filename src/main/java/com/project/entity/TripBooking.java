package com.project.entity;

public class TripBooking {

    private String tripNo;
    private int guestNumber;

    public TripBooking(String tripNo, int guestNumber) {
        this.tripNo = tripNo;
        this.guestNumber = guestNumber;
    }

    public TripBooking() {
    }

    public String getTripNo() {
        return tripNo;
    }

    public void setTripNo(String tripNo) {
        this.tripNo = tripNo;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }
}
