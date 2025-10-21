package com.cehn17.AttendanceServer.service;

import com.cehn17.AttendanceServer.dto.UserDTO;
import com.cehn17.AttendanceServer.entities.Project;
import com.cehn17.AttendanceServer.entities.User;
import com.cehn17.AttendanceServer.enums.UserRole;
import com.cehn17.AttendanceServer.repository.ProjectRepository;
import com.cehn17.AttendanceServer.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public UserDTO createUser(UserDTO dto) {
        boolean exists = userRepository.findByEmail(dto.getEmail()).isPresent();
        if (exists) {
            throw new EntityExistsException("User already exists");
        }
        Optional<Project> optionalProject = projectRepository.findById(dto.getProjectId());
        if (optionalProject.isPresent()) {
            User user = new User();
            user.setUserRole(dto.getUserRole());
            user.setPassword(dto.getPassword());
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
            user.setProject(optionalProject.get());
            User userCreated = userRepository.save(user);
            return userCreated.getDto();
        } else {
            throw new EntityNotFoundException("Project Not Found");
        }
    }

    public List<UserDTO> getAllManagers(){
        List<User> users = userRepository.findAllByUserRole(UserRole.MANAGER);
        return users.stream().map(User::getDto).collect(Collectors.toList());
    }
}
