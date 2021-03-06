package com.example.TFI.Controllers;

import com.example.TFI.DTO.PatientDTO;
import com.example.TFI.DTO.PatientListDTO;
import com.example.TFI.Models.Patient;
import com.example.TFI.Services.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/patients")
@Tag(name="Pacientes")
public class PatientController {
    private static final Logger logger = Logger.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Listar pacientes")
    public ResponseEntity<List<PatientListDTO>> listPatients() {
        try {
            List<Patient> patients = patientService.listPatients();
            if (patients == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            List<PatientListDTO> patientsDTO = patients.stream().map(patient -> new ObjectMapper().convertValue(patient, PatientListDTO.class)).collect(Collectors.toList());
            return ResponseEntity.ok(patientsDTO);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    @Operation(summary = "Registrar paciente")
    public ResponseEntity createPatient(@RequestBody Patient newPatient) {
        try {
            Patient patient = patientService.createPatient(newPatient);
            if (patient == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ingresado ya existe");
            PatientDTO patientDTO = new ObjectMapper().convertValue(patient, PatientDTO.class);
            return ResponseEntity.ok(patientDTO);
        } catch (DataIntegrityViolationException error) {
            String errorMessage = "El nombre de usuario ya existe";
            if (error.getMessage() != null && error.getMessage().contains("DNI"))
                errorMessage = "Ya existe un paciente con el mismo DNI";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Obtener paciente por ID")
    public ResponseEntity readPatient(@PathVariable int id) {
        Patient patient = patientService.getPatient(id);
        if (patient == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un paciente con el ID proporcionado");
        PatientDTO patientDTO = new ObjectMapper().convertValue(patient, PatientDTO.class);
        return ResponseEntity.ok(patientDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    @Operation(summary = "Actualizar paciente")
    public ResponseEntity updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
        try {
            Patient patient = patientService.updatePatient(id, updatedPatient);
            if (patient == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un paciente con el ID proporcionado");
            PatientDTO patientDTO = new ObjectMapper().convertValue(patient, PatientDTO.class);
            return ResponseEntity.ok(patientDTO);
        } catch (DataIntegrityViolationException error) {
            String errorMessage = "El nombre de usuario ya existe";
            if (error.getMessage() != null && error.getMessage().contains("DNI"))
                errorMessage = "Ya existe un paciente con el mismo DNI";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    @Operation(summary = "Eliminarr pacientes por ID")
    public ResponseEntity deletePatient(@PathVariable int id) {
        try {
            boolean success = patientService.deletePatient(id);
            if (!success)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un paciente con el ID proporcionado");
            return ResponseEntity.ok(true);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
