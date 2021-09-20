package com.example.TFI.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "id", nullable = false)
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    public Appointment() {
    }
}
