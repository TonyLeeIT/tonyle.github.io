package com.project.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "bus")
public class Bus {
    @Id
    private String busPlateNumber;
    private String color;
    private String manufacturer;
    private String line;
    private String model;
    private int seatNo;
    private int yearUsed;
    private java.sql.Date lastWarrantyDate;
    private int dayLeft;


    public String getBusPlateNumber() {
        return busPlateNumber;
    }

    public void setBusPlateNumber(String busPlateNumber) {
        this.busPlateNumber = busPlateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getYearUsed() {
        return yearUsed;
    }

    public void setYearUsed(int yearUsed) {
        this.yearUsed = yearUsed;
    }

    public java.sql.Date getLastWarrantyDate() {
        return lastWarrantyDate;
    }

    public void setLastWarrantyDate(java.sql.Date lastWarrantyDate) {
        this.lastWarrantyDate = lastWarrantyDate;
    }

    public int getDayLeft() {
        return dayLeft;
    }

    public void setDayLeft(int dayLeft) {
        this.dayLeft = dayLeft;
    }
}
