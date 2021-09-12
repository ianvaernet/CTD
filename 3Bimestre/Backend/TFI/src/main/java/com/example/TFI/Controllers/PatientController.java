package com.example.TFI.Controllers;

import com.example.TFI.DAO.PatientDAOJDBC;
import com.example.TFI.DAO.H2Database;
import com.example.TFI.DAO.UserDAOJDBC;
import com.example.TFI.DTO.PatientDTO;
import com.example.TFI.DTO.PatientListDTO;
import com.example.TFI.Models.Patient;
import com.example.TFI.Services.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private static final Logger logger = Logger.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController() {
        this.patientService = new PatientService(H2Database.getConnection(), new PatientDAOJDBC(H2Database.getConnection()), new UserDAOJDBC(H2Database.getConnection()));
    }

    @GetMapping
    @ResponseBody
    @CrossOrigin("*")
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
    @CrossOrigin("*")
    public ResponseEntity createPatient(@RequestBody Patient newPatient) {
        try {
            Patient patient = patientService.createPatient(newPatient);
            if (patient == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ingresado ya existe");
            PatientDTO patientDTO = new ObjectMapper().convertValue(patient, PatientDTO.class);
            return ResponseEntity.ok(patientDTO);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity readPatient(@PathVariable int id) {
        Patient patient = patientService.getPatient(id);
        if (patient == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un paciente con el ID proporcionado");
        PatientDTO patientDTO = new ObjectMapper().convertValue(patient, PatientDTO.class);
        return ResponseEntity.ok(patientDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
        try {
            Patient patient = patientService.updatePatient(id, updatedPatient);
            if (patient == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un paciente con el ID proporcionado");
            PatientDTO patientDTO = new ObjectMapper().convertValue(patient, PatientDTO.class);
            return ResponseEntity.ok(patientDTO);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity deletePatient(@PathVariable int id) {
        try {
            boolean success = patientService.deletePatient(id);
            if (!success)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un paciente con el ID proporcionado");
            return ResponseEntity.ok(true);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
