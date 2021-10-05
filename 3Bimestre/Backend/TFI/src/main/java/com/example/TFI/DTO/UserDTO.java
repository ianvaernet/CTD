package com.example.TFI.DTO;

import com.example.TFI.Models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    public Integer id;
    public String username;
    public String firstName;
    public String lastName;
    public Role role;
    public String token;
}
