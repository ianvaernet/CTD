package com.example.TFI.Models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User {
    @Column(nullable = false, unique = true)
    private Integer DNI;
    @Column(nullable = false)
    @CreationTimestamp
    private Date entryDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Patient() {
    }

    public Patient(String username, String password, String firstName, String lastName, int DNI, Date entryDate, Address address) {
        super(Role.PATIENT, username, password, firstName, lastName);
        this.DNI = DNI;
        this.entryDate = entryDate;
        this.address = address;
    }

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "DNI=" + DNI +
                ", entryDate=" + entryDate +
                ", address=" + address +
                "} " + super.toString();
    }
}
