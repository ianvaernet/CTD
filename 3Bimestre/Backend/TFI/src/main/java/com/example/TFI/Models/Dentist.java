package com.example.TFI.Models;

public class Dentist extends User {
    private int licenseNumber;

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
