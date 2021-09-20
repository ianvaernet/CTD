package com.example.TFI.Models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = true)
    private Integer floor;
    @Column(nullable = true)
    private String apartment;

    public Address() {
    }

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    public Address(String street, int number, int floor, String apartment) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", floor=" + floor +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
