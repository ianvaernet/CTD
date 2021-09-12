package com.example.TFI.Models;

public class Patient extends User {

    public Patient(String username, String password, String firstName, String lastName) {
        super(Role.PATIENT, username, password, firstName, lastName);
    }

}
