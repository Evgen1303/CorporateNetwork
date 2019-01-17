package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.entity.EmployeeProject;
import org.springframework.stereotype.Service;


@Service
public class EmployeeProjectConverterService {
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
        EmployeeProject employeeProject = new EmployeeProject();
        //employeeProject.setEmployee(ProjectQueryService.getEmployeeProjectById(employeeProjectDto.getEmployee()));
        //employeeProject.setProject(projectQueryService.getProjectById(employeeProjectDto.getProject()));
        employeeProject.setPosition(employeeProjectDto.getPosition());
        employeeProject.setStart(employeeProjectDto.getStart());
        employeeProject.setEnd(employeeProjectDto.getEnd());
        return employeeProject;
    }
}
