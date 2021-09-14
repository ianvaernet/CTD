package com.example.TFI.Models;

import javax.persistence.*;


@Entity
@Table(name = "patients")
public class Patient extends User {
//    private int id;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;

    public Patient() {
    }

    public Patient(String username, String password, String firstName, String lastName) {
        super(Role.PATIENT, username, password, firstName, lastName);
    }

}
