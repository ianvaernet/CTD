package com.example.TFI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTO {
    public int licenseNumber;
    public String username;
    public String firstName;
    public String lastName;
}
