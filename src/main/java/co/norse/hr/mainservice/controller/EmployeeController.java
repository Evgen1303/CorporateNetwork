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
    public @ResponseBody String getAllEmployees() {
        return "200 OK\n\n" + employeeControllerService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public @ResponseBody String getEmployee(@PathVariable Long id) {
        return "200 OK\n\n" + employeeControllerService.getEmployeeById(id);
    }

    @PostMapping("/create")
    public @ResponseBody String createEmployee (@RequestBody Employee employee) {
        employeeControllerService.saveEmployee(employee);
        return "201 CREATED\n\nlocation=#/employees/" + employee.getId();
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody String deleteEmployee (@RequestBody Long id) {
        employeeControllerService.deleteEmployeeById(id);
        return "200 OK";
    }

}
