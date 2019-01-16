package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeDTO;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.expection.EmployeeNotFoundException;
import co.norse.hr.mainservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeQueryService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeQueryService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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

    public EmployeeDTO patchEmployee (EmployeeDTO employeeDto, Long id) {
        EmployeeDTO oldEmployeeDto = this.getEmployeeById(id).convertToDto();
        if (employeeDto.getBirthday()==0) {
            employeeDto.setBirthday(oldEmployeeDto.getBirthday());
        }
        if (employeeDto.getCompanyId()==0) {
            employeeDto.setCompanyId(oldEmployeeDto.getCompanyId());
        }
        if (employeeDto.getDescription().length()==0) {
            employeeDto.setDescription(oldEmployeeDto.getDescription());
        }
        if (employeeDto.getEmail().length()==0) {
            employeeDto.setEmail(oldEmployeeDto.getEmail());
        }
        if (employeeDto.getFirstName().length()==0) {
            employeeDto.setFirstName(oldEmployeeDto.getFirstName());
        }
        if (employeeDto.getLastName().length()==0) {
            employeeDto.setLastName(oldEmployeeDto.getLastName());
        }
        if (employeeDto.getOfficeId()==0) {
            employeeDto.setOfficeId(oldEmployeeDto.getOfficeId());
        }
        if (employeeDto.getPhone().length()==0) {
            employeeDto.setPhone(oldEmployeeDto.getPhone());
        }
        if (employeeDto.getPosition().length()==0) {
            employeeDto.setPosition(oldEmployeeDto.getPosition());
        }
        if (employeeDto.getRoomNumber().length()==0) {
            employeeDto.setRoomNumber(oldEmployeeDto.getRoomNumber());
        }
        this.updateEmployee(employeeDto.convertToEntity());
        return employeeDto;
    }
}
