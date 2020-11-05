package com.project.entity;


import java.sql.Date;

public class BusTemp extends Bus{
    private java.sql.Date nextWarrantyDate;
    private Boolean checkOutOfDate;
    private int revenue;

    public BusTemp(Date nextWarrantyDate, Boolean checkOutOfDate, int revenue) {
        this.nextWarrantyDate = nextWarrantyDate;
        this.checkOutOfDate = checkOutOfDate;
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public BusTemp(){}


    public Date getNextWarrantyDate() {
        return nextWarrantyDate;
    }

    public void setNextWarrantyDate(Date nextWarrantyDate) {
        this.nextWarrantyDate = nextWarrantyDate;
    }

    public Boolean getCheckOutOfDate() {
        return checkOutOfDate;
    }

    public void setCheckOutOfDate(Boolean checkOutOfDate) {
        this.checkOutOfDate = checkOutOfDate;
    }

}
