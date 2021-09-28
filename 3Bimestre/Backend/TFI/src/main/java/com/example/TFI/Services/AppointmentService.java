package com.example.TFI.Services;

import com.example.TFI.Models.Appointment;
import com.example.TFI.Persistence.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;

    public List<Appointment> listAppointments(Integer dentistId, Integer patientId) {
        if (dentistId != null) return appointmentRepository.findByDentistId(dentistId);
        if (patientId != null) return appointmentRepository.findByPatientId(patientId);
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment newAppointment) {
        appointmentRepository.save(newAppointment);
        return newAppointment;
    }

    public boolean deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
        return true;
    }
}
