package com.example.TFI.Persistence;

import com.example.TFI.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    public User getByUsername(String username);
}
