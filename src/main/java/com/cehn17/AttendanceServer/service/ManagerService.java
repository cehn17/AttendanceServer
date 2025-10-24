package com.cehn17.AttendanceServer.service;

import com.cehn17.AttendanceServer.dto.UserDTO;
import com.cehn17.AttendanceServer.entities.User;
import com.cehn17.AttendanceServer.enums.UserRole;
import com.cehn17.AttendanceServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllEmployeesByProject (Long projectId) {
        List<User> users = userRepository.findAllByProjectIdAndUserRole(projectId, UserRole.EMPLOYEE);
        return users.stream().map(User::getDto).collect(Collectors.toList());
    }
}
