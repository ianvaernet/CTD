package com.example.TFI.DTO;

import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.Patient;
import java.time.LocalDateTime;

public class AppointmentListDTO {
    public LocalDateTime dateTime;
    public Dentist dentist;
    public Patient patient;
}
