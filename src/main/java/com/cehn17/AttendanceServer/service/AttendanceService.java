package com.cehn17.AttendanceServer.service;

import com.cehn17.AttendanceServer.dto.AttendanceDTO;
import com.cehn17.AttendanceServer.entities.Attendance;
import com.cehn17.AttendanceServer.entities.Project;
import com.cehn17.AttendanceServer.entities.User;
import com.cehn17.AttendanceServer.repository.AttendanceRepository;
import com.cehn17.AttendanceServer.repository.ProjectRepository;
import com.cehn17.AttendanceServer.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public AttendanceDTO markAttendance (AttendanceDTO dto){

        Optional<Attendance> optionalAttendance = attendanceRepository.findByEmployeeIdAndProjectIdAndDate(
                dto.getEmployeeId(), dto.getProjectId(), LocalDate.now()
        ) ;

        if(optionalAttendance.isEmpty()){
            Optional<User> optionalEmployee = userRepository.findById(dto.getEmployeeId());
            Optional<User> optionalManager = userRepository.findById(dto.getManagerId());
            Optional<Project> optionalProject= projectRepository.findById(dto.getProjectId());

            if(optionalEmployee.isPresent() && optionalManager.isPresent() && optionalProject.isPresent()){
                Attendance attendance = new Attendance();
                attendance. setDate(LocalDate.now());
                attendance. setAttendanceStatus(dto.getAttendanceStatus());
                attendance. setEmployee(optionalEmployee.get());
                attendance.setProject(optionalProject.get());
                attendance. setManager(optionalManager.get());
                return attendanceRepository.save(attendance).getDto();
            }else{
                throw new EntityNotFoundException("Some Related Entity Not Found");
            }
        }
        else{
            throw new EntityExistsException("Attendance Already Marked For Today");
        }

    }
}
