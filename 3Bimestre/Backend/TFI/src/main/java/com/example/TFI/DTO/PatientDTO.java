package com.example.TFI.DTO;

import com.example.TFI.Models.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTO {
    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public int dni;
    public Date entryDate;
    public Address address;
}
