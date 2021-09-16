package com.example.TFI.Controllers;

import com.example.TFI.DTO.DentistDTO;
import com.example.TFI.DTO.DentistListDTO;
import com.example.TFI.Models.Dentist;
import com.example.TFI.Services.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {
    private static final Logger logger = Logger.getLogger(DentistController.class);
    @Autowired
    private DentistService dentistService;

    @GetMapping
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity<List<DentistListDTO>> listDentists() {
        try {
            List<Dentist> dentists = dentistService.listDentists();
            if (dentists == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            List<DentistListDTO> dentistsDTO = dentists.stream().map(dentist -> new ObjectMapper().convertValue(dentist, DentistListDTO.class)).collect(Collectors.toList());
            return ResponseEntity.ok(dentistsDTO);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity createDentist(@RequestBody Dentist newDentist) {
        try {
            Dentist dentist = dentistService.createDentist(newDentist);
            if (dentist == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ingresado ya existe");
            DentistDTO dentistDTO = new ObjectMapper().convertValue(dentist, DentistDTO.class);
            return ResponseEntity.ok(dentistDTO);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity readDentist(@PathVariable int id) {
        Dentist dentist = dentistService.getDentist(id);
        if (dentist == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un odontólogo con el ID proporcionado");
        DentistDTO dentistDTO = new ObjectMapper().convertValue(dentist, DentistDTO.class);
        return ResponseEntity.ok(dentistDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity updateDentist(@PathVariable int id, @RequestBody Dentist updatedDentist) {
        try {
            Dentist dentist = dentistService.updateDentist(id, updatedDentist);
            if (dentist == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un odontólogo con el ID proporcionado");
            DentistDTO dentistDTO = new ObjectMapper().convertValue(dentist, DentistDTO.class);
            return ResponseEntity.ok(dentistDTO);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity deleteDentist(@PathVariable int id) {
        try {
            boolean success = dentistService.deleteDentist(id);
            if (!success)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un odontólogo con el ID proporcionado");
            return ResponseEntity.ok(true);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
