package com.example.TFI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTO {
    public int id;
    public String username;
    public String firstName;
    public String lastName;
}
