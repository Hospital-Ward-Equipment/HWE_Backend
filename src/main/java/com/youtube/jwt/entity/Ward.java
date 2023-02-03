package com.youtube.jwt.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ward")
public class Ward  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ward_id;

    @Column(name = "name")
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String ward_name;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Ward() {
    }

    public Ward(int ward_id, String ward_name, List<Product> products) {
        this.ward_id = ward_id;
        this.ward_name = ward_name;
    }

    public Ward(String ward_name, List<Product> products) {
        this.ward_id = ward_id;
        this.ward_name = ward_name;
    }

    public int getWard_id() {
        return ward_id;
    }

    public void setWard_id(int ward_id) {
        this.ward_id = ward_id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

}
