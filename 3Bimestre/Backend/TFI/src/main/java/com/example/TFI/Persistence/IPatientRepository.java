package com.example.TFI.Persistence;

import com.example.TFI.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPatientRepository extends JpaRepository<Patient,Integer> {
    @Query("SELECT p FROM Patient p INNER JOIN p.address WHERE p.id = ?1")
    public Patient get(int id);
}
