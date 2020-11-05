package com.project.entity;


public class BusRevenue implements IBusRevenue{
    private String busPlateNumber;
    private long revenue;

    public BusRevenue() {
    }

    public BusRevenue(String busPlateNumber, long revenue) {
        this.busPlateNumber = busPlateNumber;
        this.revenue = revenue;
    }

    public void setBusPlateNumber(String busPlateNumber) {
        this.busPlateNumber = busPlateNumber;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    @Override
    public String getBusPlateNumber() {
        return busPlateNumber;
    }

    @Override
    public long getRevenue() {
        return revenue;
    }
}
