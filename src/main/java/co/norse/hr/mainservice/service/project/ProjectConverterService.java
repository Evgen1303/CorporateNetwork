package co.norse.hr.mainservice.service.project;

import co.norse.hr.mainservice.dto.ProjectDto;
import co.norse.hr.mainservice.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectConverterService {


    private ModelMapper modelMapper = new ModelMapper();

    public ProjectDto convertToDto(Project project) {
        return modelMapper.map(project, ProjectDto.class);
    }

    public Project convertToEntity(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }

}
