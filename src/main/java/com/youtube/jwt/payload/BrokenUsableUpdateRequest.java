package com.youtube.jwt.payload;

public class BrokenUsableUpdateRequest {
    private int id;
    private int usable;
    private int broken;

    public BrokenUsableUpdateRequest() {
    }

    public BrokenUsableUpdateRequest(int id, int usable, int broken) {
        id = id;
        this.usable = usable;
        this.broken = broken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }

    public int getBroken() {
        return broken;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }
}
