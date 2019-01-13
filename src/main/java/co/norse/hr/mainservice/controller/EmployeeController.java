package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeControllerService employeeControllerService;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeControllerService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeControllerService.getEmployeeById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee (@RequestBody Employee employee) {
        employeeControllerService.saveEmployee(employee);
        try {
            URI location = new URI("#/employees/" + employee.getId());
            return ResponseEntity.created(location).build();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
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
