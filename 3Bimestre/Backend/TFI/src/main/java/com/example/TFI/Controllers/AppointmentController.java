package com.example.TFI.Controllers;

import com.example.TFI.DTO.AppointmentDTO;
import com.example.TFI.DTO.CreateAppointmentDTO;
import com.example.TFI.Models.Appointment;
import com.example.TFI.Models.Dentist;
import com.example.TFI.Models.Patient;
import com.example.TFI.Services.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private static final Logger logger = Logger.getLogger(AppointmentController.class);
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity<List<AppointmentDTO>> listAppointments(@RequestParam(required = false) Integer dentistId, @RequestParam(required = false) Integer patientId) {
        try {
            List<Appointment> appointments = appointmentService.listAppointments(dentistId, patientId);
            if (appointments == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            List<AppointmentDTO> appointmentsDTO = appointments.stream().map(appointment -> new ObjectMapper().registerModule(new JavaTimeModule()).convertValue(appointment, AppointmentDTO.class)).collect(Collectors.toList());
            return ResponseEntity.ok(appointmentsDTO);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity<Appointment> createAppointment(@RequestBody CreateAppointmentDTO newAppointment) {
        try {
            Appointment appointment = new Appointment();
            Dentist dentist = new Dentist();
            dentist.setId(newAppointment.dentistId);
            Patient patient = new Patient();
            patient.setId(newAppointment.patientId);
            appointment.setDateTime(newAppointment.dateTime);
            appointment.setDentist(dentist);
            appointment.setPatient(patient);
            return ResponseEntity.ok(appointmentService.createAppointment(appointment));
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("*")
    public ResponseEntity deleteAppointment(@PathVariable int id) {
        try {
            boolean success = appointmentService.deleteAppointment(id);
            if (!success)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un turno con el ID proporcionado");
            return ResponseEntity.ok(true);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
