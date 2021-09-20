package com.example.TFI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientListDTO {
    public int id;
    public String fullName;
    public int dni;
}
