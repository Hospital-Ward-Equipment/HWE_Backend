package com.youtube.jwt.payload;

public class BrokenChartResponse {
    private int id;
    private String name;
    private int broken;
    private int usable;
    private int count;

    public BrokenChartResponse(int id, String name, int broken, int usable, int count) {
        this.id = id;
        this.name = name;
        this.broken = broken;
        this.usable = usable;
        this.count = count;
    }

    public BrokenChartResponse() {
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBroken() {
        return broken;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }
}
