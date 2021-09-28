package com.example.TFI.Persistence;

import com.example.TFI.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment,Integer> {
    public List<Appointment> findByDentistId(int dentistId);
    public List<Appointment> findByPatientId(int patientId);
}
