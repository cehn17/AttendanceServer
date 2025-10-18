package com.cehn17.AttendanceServer.dto;

import com.cehn17.AttendanceServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole userRole;
}
