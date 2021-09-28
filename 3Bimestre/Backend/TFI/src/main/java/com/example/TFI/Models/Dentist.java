package com.example.TFI.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dentists")
public class Dentist extends User {
    @Column(nullable = false, unique = true)
    private Integer licenseNumber;
    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Dentist() {
    }

    public Dentist(String username, String password, int licenseNumber, String firstName, String lastName) {
        super(Role.DENTIST, username, password, firstName, lastName);
        this.licenseNumber = licenseNumber;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "licenseNumber=" + licenseNumber +
                "} " + super.toString();
    }
}
