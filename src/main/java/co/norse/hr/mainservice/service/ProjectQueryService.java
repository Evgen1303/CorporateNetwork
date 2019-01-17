package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.dto.ProjectDto;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.exception.EmployeeProjectNotFoundException;
import co.norse.hr.mainservice.exception.ProjectNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeProjectRepository;
import co.norse.hr.mainservice.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryService {

    private ProjectRepository projectRepository;
    private EmployeeProjectRepository employeeprojectRepository;

    @Autowired
    private ProjectConverterService projectConverterService = new ProjectConverterService();
    @Autowired
    private EmployeeProjectConverterService employeeProjectConverterService = new EmployeeProjectConverterService();

    @Autowired
    public ProjectQueryService(ProjectRepository projectRepository, EmployeeProjectRepository employeeprojectRepository) {
        this.projectRepository = projectRepository;
        this.employeeprojectRepository = employeeprojectRepository;
    }

    public Project getProjectById(Long id) {
        Optional<Project> result = projectRepository.findById(id);
        if (!result.isPresent()) {
            throw new ProjectNotFoundException();
        }
        return result.get();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }


    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    public void patchProject(ProjectDto projectDto, Long id) {
        ProjectDto oldProjectDto = projectConverterService.convertToDto(this.getProjectById(id));
        if (projectDto.getDescription().length() == 0) {
            projectDto.setDescription(oldProjectDto.getDescription());
        }
        if (projectDto.getName().length() == 0) {
            projectDto.setName(oldProjectDto.getName());
        }
        this.updateProject(projectConverterService.convertToEntity(projectDto));
    }

    ////////////////////////
    public EmployeeProject getEmployeeProjectById(Long id) {
        Optional<EmployeeProject> result = employeeprojectRepository.findById(id);
        if (!result.isPresent()) {
            throw new EmployeeProjectNotFoundException();
        }
        return result.get();
    }

    public void saveEmployeeProject(EmployeeProject employeeProject) {
        employeeprojectRepository.save(employeeProject);
    }

    public Iterable<EmployeeProject> getAllEmployeeProjects() {
        Iterable<EmployeeProject> result = employeeprojectRepository.findAll();
        if (!result.iterator().hasNext()) {
            throw new EmployeeProjectNotFoundException();
        }
        return result;
    }

    public void deleteEmployeeProjectById(Long id) {
        employeeprojectRepository.deleteById(id);
    }

    public void updateEmployeeProject(EmployeeProject employeeProject) {
        employeeprojectRepository.save(employeeProject);
    }

    public void patchEmployeeProject(EmployeeProjectDto employeeProjectDto, Long id) {
        EmployeeProjectDto oldEmployeeProjectDto = employeeProjectConverterService.convertToDto(this.getEmployeeProjectById(id));
        if (employeeProjectDto.getProject() == 0) {
            employeeProjectDto.setProject(oldEmployeeProjectDto.getProject());
        }
        if (employeeProjectDto.getEmployee() == 0) {
            employeeProjectDto.setEmployee(oldEmployeeProjectDto.getEmployee());
        }
        if (employeeProjectDto.getPosition().length() == 0) {
            employeeProjectDto.setPosition(oldEmployeeProjectDto.getPosition());
        }
        if (employeeProjectDto.getStart() == 0) {
            employeeProjectDto.setStart(oldEmployeeProjectDto.getStart());
        }
        if (employeeProjectDto.getEnd() == 0) {
            employeeProjectDto.setEnd(oldEmployeeProjectDto.getEnd());
        }
        this.updateEmployeeProject(employeeProjectConverterService.convertToEntity(employeeProjectDto));
    }


}
