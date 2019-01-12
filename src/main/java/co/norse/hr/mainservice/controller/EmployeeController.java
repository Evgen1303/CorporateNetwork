package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //@GetMapping("")

}
