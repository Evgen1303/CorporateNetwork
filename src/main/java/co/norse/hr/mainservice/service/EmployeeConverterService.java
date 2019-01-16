package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConverterService {

    /*
    private CompanyQueryService companyQueryService;
    private OfficeQueryService officeQueryService;

    @Autowired
    EmployeeConverterService(CompanyQueryService companyQueryService, OfficeQueryService officeQueryService) {
        this.companyQueryService = companyQueryService;
        this.officeQueryService = officeQueryService;
    }
    */


    public EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setBirthday(employee.getBirthday());
        dto.setCompanyId(employee.getCompany().getId());
        dto.setDescription(employee.getDescription());
        dto.setEmail(employee.getEmail());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setOfficeId(employee.getOffice().getId());
        dto.setPosition(employee.getPosition());
        dto.setRoomNumber(employee.getRoomNumber());
        return dto;
    }

    public Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setBirthday(employeeDto.getBirthday());
        //employee.setCompany(companyQueryService.getCompanyById(employeeDto.getCompanyId()));
        employee.setDescription(employeeDto.getDescription());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        //employee.setOffice(officeQueryService.getOfficeById(employeeDto.getOfficeId()));
        employee.setPosition(employeeDto.getPosition());
        employee.setRoomNumber(employeeDto.getRoomNumber());
        return employee;
    }
}
