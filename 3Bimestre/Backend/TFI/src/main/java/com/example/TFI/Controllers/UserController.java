package com.example.TFI.Controllers;

import com.example.TFI.DTO.LoginDataDTO;
import com.example.TFI.DTO.PatientDTO;
import com.example.TFI.DTO.UserDTO;
import com.example.TFI.Models.Patient;
import com.example.TFI.Models.User;
import com.example.TFI.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@Tag(name="Auth")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity login(@RequestBody LoginDataDTO loginData) {
        try {
            User user = userService.login(loginData.username, loginData.password);
            if (user == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario y/o contraseña incorrectos");
            UserDTO userDTO = new ObjectMapper().convertValue(user, UserDTO.class);
            userDTO.token = userService.getJWTToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(userDTO);
        } catch (Exception error) {
            logger.error(error.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
