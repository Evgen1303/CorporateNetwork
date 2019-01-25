package co.norse.hr.mainservice.service.employeeproject;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.exception.RequestValidationException;
import co.norse.hr.mainservice.service.employee.EmployeeQueryService;
import co.norse.hr.mainservice.service.project.ProjectQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class EmployeeProjectConverterService {
    private EmployeeQueryService employeeQueryService;
    private ProjectQueryService projectQueryService;

    @Autowired
    public EmployeeProjectConverterService(EmployeeQueryService employeeQueryService, ProjectQueryService projectQueryService) {
        this.employeeQueryService = employeeQueryService;
        this.projectQueryService = projectQueryService;
    }

    public EmployeeProjectDto convertToDto(EmployeeProject employeeProject) {
        EmployeeProjectDto dto = new EmployeeProjectDto();
        dto.setEmployee(employeeProject.getEmployee().getId());
        dto.setProject(employeeProject.getProject().getId());
        dto.setPosition(employeeProject.getPosition());
        dto.setEnd(employeeProject.getEnd());
        dto.setStart(employeeProject.getStart());
        return dto;
    }

    public EmployeeProject convertToEntity(EmployeeProjectDto employeeProjectDto) {
        Project project;
        try {
            project = projectQueryService.findOneOrThrowException(employeeProjectDto.getProject());

        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Project not found");
        }

        Employee employee;
        try {
            employee = employeeQueryService.findOneOrThrowException(employeeProjectDto.getEmployee());

        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Employee not found");
        }
        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setProject(project);
        employeeProject.setEmployee(employee);
        employeeProject.setPosition(employeeProjectDto.getPosition());
        employeeProject.setStart(employeeProjectDto.getStart());
        employeeProject.setEnd(employeeProjectDto.getEnd());
        return employeeProject;
    }
}
