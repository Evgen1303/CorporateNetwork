package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
