package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeDTO;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public final class EmployeeController {

    private EmployeeQueryService employeeQueryService;

    @Autowired
    public EmployeeController(EmployeeQueryService employeeQueryService) {
        this.employeeQueryService = employeeQueryService;
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeQueryService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeQueryService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee (@RequestBody Employee employee) {
        employeeQueryService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee (@PathVariable Long id) {
        employeeQueryService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }


    //TODO
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeePut (@RequestParam Long id,
                                                                     @RequestBody EmployeeDTO employee) {
        employeeQueryService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    //TODO: PATCH

}
