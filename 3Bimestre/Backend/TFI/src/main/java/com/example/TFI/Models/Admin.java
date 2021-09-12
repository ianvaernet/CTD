package com.example.TFI.Models;

public class Admin extends User {
    public Admin(String username, String password, String first_name, String last_name) {
        super(Role.ADMIN, username, password, first_name, last_name);
    }
}
