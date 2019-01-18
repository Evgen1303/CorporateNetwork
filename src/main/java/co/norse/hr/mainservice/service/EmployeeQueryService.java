package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.exception.EmployeeNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmployeeQueryService {
    private EmployeeRepository employeeRepository;
    private EmployeeConverterService employeeConverterService;

    @Autowired
    public EmployeeQueryService(EmployeeRepository employeeRepository, EmployeeConverterService employeeConverterService) {
        this.employeeRepository = employeeRepository;
        this.employeeConverterService = employeeConverterService;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElseThrow(EmployeeNotFoundException::new);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Iterable<Employee> getAllEmployees() {
        Iterable<Employee> result = employeeRepository.findAll();
        if (!result.iterator().hasNext()) {
            throw new EmployeeNotFoundException();
        }
        return result;
    }

    public Page<Employee> getPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void patchEmployee(EmployeeDto employeeDto, Long id) {
        EmployeeDto oldEmployeeDto = employeeConverterService.convertToDto(this.getEmployeeById(id));
        if (employeeDto.getBirthday() == 0) {
            employeeDto.setBirthday(oldEmployeeDto.getBirthday());
        }
        if (employeeDto.getCompanyId() == 0) {
            employeeDto.setCompanyId(oldEmployeeDto.getCompanyId());
        }
        if (employeeDto.getDescription().length() == 0) {
            employeeDto.setDescription(oldEmployeeDto.getDescription());
        }
        if (employeeDto.getEmail().length() == 0) {
            employeeDto.setEmail(oldEmployeeDto.getEmail());
        }
        if (employeeDto.getFirstName().length() == 0) {
            employeeDto.setFirstName(oldEmployeeDto.getFirstName());
        }
        if (employeeDto.getLastName().length() == 0) {
            employeeDto.setLastName(oldEmployeeDto.getLastName());
        }
        if (employeeDto.getOfficeId() == 0) {
            employeeDto.setOfficeId(oldEmployeeDto.getOfficeId());
        }
        if (employeeDto.getPhone().length() == 0) {
            employeeDto.setPhone(oldEmployeeDto.getPhone());
        }
        if (employeeDto.getPosition().length() == 0) {
            employeeDto.setPosition(oldEmployeeDto.getPosition());
        }
        if (employeeDto.getRoomNumber().length() == 0) {
            employeeDto.setRoomNumber(oldEmployeeDto.getRoomNumber());
        }
        this.updateEmployee(employeeConverterService.convertToEntity(employeeDto));
    }
}
