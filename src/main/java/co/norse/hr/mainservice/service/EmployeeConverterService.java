package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.exception.RequestValidationException;
import co.norse.hr.mainservice.repositories.CompanyRepository;
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
        //check if fields are blank or not

        Company company;
        Office office;
        try {
            company = companyQueryService.findOneOrThrowException(employeeDto.getCompanyId());
            office = officeQueryService.findOneOrThrowException(employeeDto.getOfficeId());
        } catch (EntityNotFoundException e) {
            //todo add message
            throw new RequestValidationException();
        }


        Employee employee = new Employee();
        employee.setBirthday(employeeDto.getBirthday());
        employee.setCompany(company);
        employee.setDescription(employeeDto.getDescription());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setOffice(office);
        employee.setPosition(employeeDto.getPosition());
        employee.setRoomNumber(employeeDto.getRoomNumber());
        return employee;
    }
}
