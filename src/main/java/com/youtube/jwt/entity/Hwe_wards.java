package com.youtube.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hwe_wards")
public class Hwe_wards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    @Column(name = "name")
    private String Name;

    @OneToMany(mappedBy = "hwe_wards")
    @JsonIgnore
    private Set<Hwe_ward_equipments> hwe_ward_equipments = new HashSet<>();

    public Hwe_wards() {
    }

    public Hwe_wards(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Hwe_ward_equipments> getHwe_ward_equipments() {
        return hwe_ward_equipments;
    }

    public void setHwe_ward_equipments(Set<Hwe_ward_equipments> hwe_ward_equipments) {
        this.hwe_ward_equipments = hwe_ward_equipments;
    }
}
