package com.youtube.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "hwe_ward_equipments")
public class Hwe_ward_equipments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ward_equipment_id")
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hwe_equipments")
    private Hwe_equipments hwe_equipments;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hwe_wards")
    private Hwe_wards hwe_wards;

    private int usable;
    private int broken;

    public Hwe_ward_equipments() {
    }

    public Hwe_ward_equipments(Hwe_equipments hwe_equipments, Hwe_wards hwe_wards, int usable, int broken) {
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

    public Hwe_equipments getHwe_equipments() {
        return hwe_equipments;
    }

    public void setHwe_equipments(Hwe_equipments hwe_equipments) {
        this.hwe_equipments = hwe_equipments;
    }

    public Hwe_wards getHwe_wards() {
        return hwe_wards;
    }

    public void setHwe_wards(Hwe_wards hwe_wards) {
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
