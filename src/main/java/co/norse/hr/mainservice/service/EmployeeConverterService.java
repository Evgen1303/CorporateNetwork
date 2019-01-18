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
        try {
            employee.setBirthday(employeeDto.getBirthday());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid birthday value");
        }
        try {
            employee.setDescription(employeeDto.getDescription());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid description value");
        }
        try {
            employee.setEmail(employeeDto.getEmail());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid email value");
        }
        try {
            employee.setFirstName(employeeDto.getFirstName());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid first name value");
        }
        try {
            employee.setLastName(employeeDto.getLastName());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid last name value");
        }
        try {
            employee.setPosition(employeeDto.getPosition());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid position value");
        }
        try {
            employee.setRoomNumber(employeeDto.getRoomNumber());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid room number value");
        }
        try {
            employee.setPhone(employeeDto.getPhone());
        } catch (EntityNotFoundException e) {
            throw new RequestValidationException("Invalid phone number value");
        }
        return employee;
    }
}
