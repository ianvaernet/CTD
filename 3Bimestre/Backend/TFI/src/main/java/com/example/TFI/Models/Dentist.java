package com.example.TFI.Models;

import javax.persistence.*;

@Entity
@Table(name = "dentists")
public class Dentist extends User {
//    @Id
//    private int id;
    private int licenseNumber;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
//    private User user;

    public Dentist() {
    }

    public Dentist(String username, String password, int licenseNumber, String firstName, String lastName) {
        super(Role.DENTIST, username, password, firstName, lastName);
        this.licenseNumber = licenseNumber;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "licenseNumber=" + licenseNumber +
                "} " + super.toString();
    }
}
