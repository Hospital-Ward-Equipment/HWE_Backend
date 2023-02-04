package com.youtube.jwt.payload;

public class BrokenWardEquipmentChart {
    private int id;
    private String wname;
    private int broken;

    public BrokenWardEquipmentChart() {
    }

    public BrokenWardEquipmentChart(int id, String wname, int broken) {
        this.id = id;
        this.wname = wname;
        this.broken = broken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public int getBroken() {
        return broken;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }
}
