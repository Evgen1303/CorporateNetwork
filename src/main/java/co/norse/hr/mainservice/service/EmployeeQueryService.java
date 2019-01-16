package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.exception.EmployeeNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeRepository;
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
        if (!result.isPresent()) {
            throw new EmployeeNotFoundException();
        }
        return result.get();
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
}
