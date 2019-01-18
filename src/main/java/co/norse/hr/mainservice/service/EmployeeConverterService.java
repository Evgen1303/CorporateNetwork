package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.exception.RequestValidationException;
import co.norse.hr.mainservice.service.company.CompanyQueryService;
import co.norse.hr.mainservice.service.office.OfficeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EmployeeConverterService {
    private final CompanyQueryService companyQueryService;
    private final OfficeQueryService officeQueryService;

    @Autowired
    public EmployeeConverterService(CompanyQueryService companyQueryService, OfficeQueryService officeQueryService) {
        this.companyQueryService = companyQueryService;
        this.officeQueryService = officeQueryService;
    }


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
        dto.setPhone(employee.getPhone());
        return dto;
    }

    public Employee convertToEntity(EmployeeDto employeeDto) {

        Office office;
        try {
            office = officeQueryService.findOneOrThrowException(employeeDto.getOfficeId());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Office not found");
        }
        Company company;
        try {
            company = companyQueryService.findOneOrThrowException(employeeDto.getCompanyId());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Company not found");
        }
        Employee employee = new Employee();
        employee.setCompany(company);
        employee.setOffice(office);
        employee.setBirthday(employeeDto.getBirthday());
        employee.setDescription(employeeDto.getDescription());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPosition(employeeDto.getPosition());
        employee.setRoomNumber(employeeDto.getRoomNumber());
        employee.setPhone(employeeDto.getPhone());
        return employee;
    }
}
