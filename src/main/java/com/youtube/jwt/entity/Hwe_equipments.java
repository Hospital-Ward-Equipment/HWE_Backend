package com.youtube.jwt.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hwe_equipments")
public class Hwe_equipments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    private String name;

    private int count;


    @OneToMany(mappedBy = "hwe_equipments", cascade = CascadeType.ALL)
    private Set<Hwe_ward_equipments> hwe_ward_equipments = new HashSet<>();


    public Hwe_equipments() {
    }

    public Hwe_equipments( String name, int count) {
        this.name = name;
        this.count = count;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<Hwe_ward_equipments> getHwe_ward_equipments() {
        return hwe_ward_equipments;
    }

    public void setHwe_ward_equipments(Set<Hwe_ward_equipments> hwe_ward_equipments) {
        this.hwe_ward_equipments = hwe_ward_equipments;
    }
}
