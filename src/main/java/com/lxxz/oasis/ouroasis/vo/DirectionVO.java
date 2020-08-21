package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

@Data
public class DirectionVO {
    private String name;

    private double heat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeat() {
        return heat;
    }

    public void setHeat(double heat) {
        this.heat = heat;
    }
}
