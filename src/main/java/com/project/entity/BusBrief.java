package com.project.entity;

public class BusBrief implements IBusBrief {

    private String busPlateNumber;
    private int seatNo;
    private String model;

    @Override
    public String getBusPlateNumber() {
        return null;
    }

    @Override
    public int getSeatNo() {
        return 0;
    }

    @Override
    public String getModel() {
        return null;
    }

    public void setBusPlateNumber(String busPlateNumber) {
        this.busPlateNumber = busPlateNumber;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
