package com.example.TFI.Persistence;

import com.example.TFI.Models.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDentistRepository extends JpaRepository<Dentist,Integer> {
}
