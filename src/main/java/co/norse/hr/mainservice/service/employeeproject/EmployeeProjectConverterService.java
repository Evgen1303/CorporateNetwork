package co.norse.hr.mainservice.service.employeeproject;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.service.employee.EmployeeQueryService;
import co.norse.hr.mainservice.service.project.ProjectQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectConverterService {
    private EmployeeQueryService employeeQueryService;
    private ProjectQueryService projectQueryService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public EmployeeProjectConverterService(EmployeeQueryService employeeQueryService,
                                           ProjectQueryService projectQueryService) {
        this.employeeQueryService = employeeQueryService;
        this.projectQueryService = projectQueryService;
    }

    public EmployeeProjectDto convertToDto(EmployeeProject employeeProject) {
        return modelMapper.map(employeeProject, EmployeeProjectDto.class);
    }

    public EmployeeProject convertToEntity(EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = modelMapper.map(employeeProjectDto, EmployeeProject.class);
        employeeProject.setEmployee(employeeQueryService.getEmployeeById(employeeProjectDto.getEmployee()));
        employeeProject.setProject(projectQueryService.getProjectById(employeeProjectDto.getProject()));
        return employeeProject;
    }
}
