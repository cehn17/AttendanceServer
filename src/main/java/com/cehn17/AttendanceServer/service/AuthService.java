package com.cehn17.AttendanceServer.service;

import com.cehn17.AttendanceServer.dto.UserDTO;
import com.cehn17.AttendanceServer.entities.User;
import com.cehn17.AttendanceServer.enums.UserRole;
import com.cehn17.AttendanceServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void createAdminUser(){
        User optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser == null) {
            User user = new User();

            user.setName("Admin");
            user.setEmail("admin@gmail.com");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword("admin");
            userRepository.save(user);
            System.out.println("Admin User Created.");
        } else {
            System.out.println("Admin User Already exists.");
        }
    }

    public UserDTO login (UserDTO user) {
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser.isPresent() && user.getPassword().equals(dbUser.get().getPassword())) {
            return dbUser.get().getDto();
        }
        return null;
    }
}