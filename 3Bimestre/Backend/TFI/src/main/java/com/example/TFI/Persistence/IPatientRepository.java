package com.example.TFI.Persistence;

import com.example.TFI.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient,Integer> {
}
