package com.example.TFI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {
    public int id;
    public LocalDateTime dateTime;
    public DentistListDTO dentist;
    public PatientListDTO patient;
}
