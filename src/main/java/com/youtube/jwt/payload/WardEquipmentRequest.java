package com.youtube.jwt.payload;

public class WardEquipmentRequest {
    private int id;
    private int hwe_equipments;
    private int hwe_wards;
    private int usable;
    private int broken;

    public WardEquipmentRequest() {
    }

    public WardEquipmentRequest(int hwe_equipments, int hwe_wards, int usable, int broken) {
        this.hwe_equipments = hwe_equipments;
        this.hwe_wards = hwe_wards;
        this.usable = usable;
        this.broken = broken;
    }

    public WardEquipmentRequest(int id, int hwe_equipments, int hwe_wards, int usable, int broken) {
        this.id = id;
        this.hwe_equipments = hwe_equipments;
        this.hwe_wards = hwe_wards;
        this.usable = usable;
        this.broken = broken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHwe_equipments() {
        return hwe_equipments;
    }

    public void setHwe_equipments(int hwe_equipments) {
        this.hwe_equipments = hwe_equipments;
    }

    public int getHwe_wards() {
        return hwe_wards;
    }

    public void setHwe_wards(int hwe_wards) {
        this.hwe_wards = hwe_wards;
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
