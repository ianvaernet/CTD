package com.example.TFI.Persistence;

import com.example.TFI.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment,Integer> {
}
