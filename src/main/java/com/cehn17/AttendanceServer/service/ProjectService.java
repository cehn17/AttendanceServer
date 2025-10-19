package com.cehn17.AttendanceServer.service;

import com.cehn17.AttendanceServer.dto.ProjectDTO;
import com.cehn17.AttendanceServer.entities.Project;
import com.cehn17.AttendanceServer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDTO addProject (ProjectDTO dto){
        Project project = new Project();
        project. setName(dto.getName());
        project.setDuration(dto.getDuration());
        project.setStartDate(dto.getStartDate());
        return projectRepository.save(project).getDto();
    }
}
