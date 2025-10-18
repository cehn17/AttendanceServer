package com.cehn17.AttendanceServer.entities;

import com.cehn17.AttendanceServer.dto.UserDTO;
import com.cehn17.AttendanceServer.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole userRole;

    public UserDTO getDto(){
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setName(name) ;
        dto.setUserRole(userRole);
        dto.setEmail(email);
        return dto;
    }

}
