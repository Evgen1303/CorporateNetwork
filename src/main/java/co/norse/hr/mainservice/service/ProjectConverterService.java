package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.ProjectDto;
import co.norse.hr.mainservice.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectConverterService {
    public ProjectDto convertToDto(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setDescription(project.getDescription());
        dto.setName(project.getName());
        return dto;
    }

    public Project convertToEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setDescription(projectDto.getDescription());
        project.setName(projectDto.getName());
        return project;
    }
}
