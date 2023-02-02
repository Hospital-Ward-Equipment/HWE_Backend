package com.youtube.jwt.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    public String name;

    @Column(name = "allCount")
    @Min(value = 0, message = "Value must be positive")
    private int allCount;

    @Column(name = "usable")
    @Min(value = 0, message = "Value must be positive")
    private int usable;

    @Column(name = "broken")
    @Min(value = 0, message = "Value must be positive")
    private int broken;

    @Column(name="ward_id")
    private int wardId;

    public Product() {
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getId() {
        return id;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }
}
