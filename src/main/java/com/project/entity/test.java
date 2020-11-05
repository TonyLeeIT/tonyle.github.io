package com.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

public class test implements ITest{
    String busPlateNumber;
    String color;

    public test() {
    }

    public test(String busPlateNumber, String color) {
        this.busPlateNumber = busPlateNumber;
        this.color = color;
    }

    @Override
    public String getBusPlateNumber() {
        return busPlateNumber;
    }

    @Override
    public String getColor() {
        return color;
    }
}
