package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
