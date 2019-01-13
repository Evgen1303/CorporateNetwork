package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public final class EmployeeController {

    private EmployeeControllerService employeeControllerService;

    @Autowired
    public EmployeeController(EmployeeControllerService employeeControllerService) {
        this.employeeControllerService = employeeControllerService;
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeControllerService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeControllerService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee (@RequestBody Employee employee) {
        employeeControllerService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee (@PathVariable Long id) {
        employeeControllerService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }


    //TODO
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeePut (@RequestParam Long id,
                                                                     @RequestBody Employee employee) {
        employee.setId(id);
        employeeControllerService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    //TODO: PATCH

}
