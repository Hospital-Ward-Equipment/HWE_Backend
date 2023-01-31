package com.youtube.jwt.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Product")
public class Product {
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

    public Product() {
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
