package co.norse.hr.mainservice.service.employeeproject;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.exception.EmployeeProjectNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeProjectQueryService {
    private EmployeeProjectRepository employeeprojectRepository;
    private EmployeeProjectConverterService employeeProjectConverterService;

    @Autowired
    public EmployeeProjectQueryService(EmployeeProjectRepository employeeprojectRepository, EmployeeProjectConverterService employeeProjectConverterService) {
        this.employeeprojectRepository = employeeprojectRepository;
        this.employeeProjectConverterService = employeeProjectConverterService;
    }

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

    public Page<EmployeeProject> getPage(Pageable pageable) {
        return employeeprojectRepository.findAll(pageable);
    }

    public void deleteEmployeeProjectById(Long id) {
        if (!employeeprojectRepository.findById(id).isPresent()) {
            throw new EmployeeProjectNotFoundException();
        }
        employeeprojectRepository.deleteById(id);
    }

    public EmployeeProject updateEmployeeProject(Long id, EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = employeeProjectConverterService.convertToEntity(employeeProjectDto);
        employeeProject.setId(id);
        employeeprojectRepository.save(employeeProject);
        return employeeProject;
    }

    public EmployeeProject patchEmployeeProject(Long id, EmployeeProjectDto employeeProjectDto) {
        EmployeeProjectDto oldEmployeeProjectDto = employeeProjectConverterService.convertToDto(this.getEmployeeProjectById(id));
        employeeProjectDto.setId(id);
        if (employeeProjectDto.getEmployee() == null) {
            employeeProjectDto.setEmployee(oldEmployeeProjectDto.getEmployee());
        }
        if (employeeProjectDto.getProject() == null) {
            employeeProjectDto.setProject(oldEmployeeProjectDto.getProject());
        }
        if (employeeProjectDto.getEnd() == 0) {
            employeeProjectDto.setEnd(oldEmployeeProjectDto.getEnd());
        }
        if (employeeProjectDto.getStart() == 0) {
            employeeProjectDto.setStart(oldEmployeeProjectDto.getStart());
        }
        if (employeeProjectDto.getPosition() == null) {
            employeeProjectDto.setPosition(oldEmployeeProjectDto.getPosition());
        }
        EmployeeProject employeeProject = employeeProjectConverterService.convertToEntity(employeeProjectDto);
        employeeProject.setId(id);
        employeeprojectRepository.save(employeeProject);
        return employeeProject;
    }

}
