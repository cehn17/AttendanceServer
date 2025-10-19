package com.cehn17.AttendanceServer.controller;

import com.cehn17.AttendanceServer.dto.UserDTO;
import com.cehn17.AttendanceServer.repository.UserRepository;
import com.cehn17.AttendanceServer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserDTO user) {
        UserDTO dbUser = authService.login(user);
        if(dbUser == null) {
            return new ResponseEntity<>("Wrong Conditionals", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }
}
