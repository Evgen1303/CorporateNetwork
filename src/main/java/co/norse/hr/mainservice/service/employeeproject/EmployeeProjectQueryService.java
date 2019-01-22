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
