package co.norse.hr.mainservice.service.project;

import co.norse.hr.mainservice.dto.ProjectDto;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.exception.ProjectNotFoundException;
import co.norse.hr.mainservice.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryService {

    private ProjectRepository projectRepository;
    private ProjectConverterService projectConverterService;

    @Autowired
    public ProjectQueryService(ProjectRepository projectRepository, ProjectConverterService projectConverterService) {
        this.projectRepository = projectRepository;
        this.projectConverterService = projectConverterService;
    }

    public Project getProjectById(Long id) {
        Optional<Project> result = projectRepository.findById(id);

        return result.orElseThrow(ProjectNotFoundException::new);
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Page<Project> getPage(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updateProject(Long id, ProjectDto projectDto) {
        Project project = projectConverterService.convertToEntity(projectDto);
        project.setId(id);
        projectRepository.save(project);
        return project;
    }

    public Project patchProject(Long id, ProjectDto projectDto) {
        Project project = projectConverterService.convertToEntity(projectDto);
        ProjectDto oldProjectDto = projectConverterService.convertToDto(this.getProjectById(id));
        oldProjectDto.setId(id);
        if (project.getDescription() == null) {
            project.setDescription(oldProjectDto.getDescription());
        }
        if (project.getName() == null) {
            project.setName(oldProjectDto.getName());
        }
        project.setId(id);
        projectRepository.save(project);
        return project;
    }
    public List<Project> getProjectByEmployeeId(Long id) {
        return projectRepository.findProjectsById(id);
    }
}
