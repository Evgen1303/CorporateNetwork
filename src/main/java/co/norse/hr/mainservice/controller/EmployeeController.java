package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody ResponseEntity<Iterable<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeControllerService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeControllerService.getEmployeeById(id));
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<Employee> createEmployee (@RequestBody Employee employee) {
        employeeControllerService.saveEmployee(employee);
        try {
            URI location = new URI("#/employees/" + employee.getId());
            return ResponseEntity.created(location).build();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/delete")
    public @ResponseBody
    ResponseEntity deleteEmployee (@RequestBody Long id) {
        employeeControllerService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/put")
    public @ResponseBody ResponseEntity<Employee> updateEmployeePut (@RequestBody Employee employee) {
        employeeControllerService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

}
