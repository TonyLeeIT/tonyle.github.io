package com.project.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String tripNo;
    Timestamp timeOrigin;
    Timestamp timeDestination;
    int guestNumber;

    int fare;

    public Trip() {
    }

    public Trip(String tripNo, Timestamp timeOrigin, Timestamp timeDestination, int guestNumber, int fare) {
        this.tripNo = tripNo;
        this.timeOrigin = timeOrigin;
        this.timeDestination = timeDestination;
        this.guestNumber = guestNumber;
        this.fare = fare;
    }

    public String getTripNo() {
        return tripNo;
    }

    public void setTripNo(String tripNo) {
        this.tripNo = tripNo;
    }

    public Timestamp getTimeOrigin() {
        return timeOrigin;
    }

    public void setTimeOrigin(Timestamp timeOrigin) {
        this.timeOrigin = timeOrigin;
    }

    public Timestamp getTimeDestination() {
        return timeDestination;
    }

    public void setTimeDestination(Timestamp timeDestination) {
        this.timeDestination = timeDestination;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }
}
