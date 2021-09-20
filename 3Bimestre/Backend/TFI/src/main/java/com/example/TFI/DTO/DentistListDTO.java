package com.example.TFI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistListDTO {
    public int id;
    public int licenseNumber;
    public String fullName;
}
