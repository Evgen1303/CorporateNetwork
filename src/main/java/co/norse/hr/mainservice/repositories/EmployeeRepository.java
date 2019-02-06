package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findByFirstNameContainingOrLastNameContaining(String name, String lastname);

}
