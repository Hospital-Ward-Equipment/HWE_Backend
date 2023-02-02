package com.youtube.jwt.dto;

public class EquipmentResponse {
    private int Id;
    private String Ename;
    private String Wname;
    private int equipment_id;
    private int ward_id;
    private int usable;
    private int broken;

    public EquipmentResponse() {
    }



    public EquipmentResponse(int id, String ename, String wname, int equipment_id, int ward_id, int usable, int broken) {
        Id = id;
        Ename = ename;
        Wname = wname;
        this.equipment_id = equipment_id;
        this.ward_id = ward_id;
        this.usable = usable;
        this.broken = broken;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public String getWname() {
        return Wname;
    }

    public void setWname(String wname) {
        Wname = wname;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public int getWard_id() {
        return ward_id;
    }

    public void setWard_id(int ward_id) {
        this.ward_id = ward_id;
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
