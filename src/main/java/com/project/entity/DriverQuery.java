package com.project.entity;

import lombok.Data;

@Data
public class DriverQuery implements IDriverQuery {
    private String driverNo;
    private String subDriverNo;
    private int wage;

    @Override
    public String getDriverNo() {
        return null;
    }

    @Override
    public String getSubDriverNo() {
        return null;
    }

    @Override
    public int getWage() {
        return 0;
    }
}
