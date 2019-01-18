package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeConverterService;
import co.norse.hr.mainservice.service.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public final class EmployeeController {

    private EmployeeQueryService employeeQueryService;
    private EmployeeConverterService employeeConverterService;

    @Autowired
    public EmployeeController(EmployeeQueryService employeeQueryService, EmployeeConverterService employeeConverterService) {
        this.employeeQueryService = employeeQueryService;
        this.employeeConverterService = employeeConverterService;
    }

    //TODO: DTO
    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeQueryService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeQueryService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeConverterService.convertToEntity(employeeDto);
        employeeQueryService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeQueryService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> putEmployee(@PathVariable Long id,
                                                @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeConverterService.convertToEntity(employeeDto);
        employeeQueryService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeQueryService.patchEmployee(employeeDto, id);
        return ResponseEntity.ok(employeeConverterService.convertToEntity(employeeDto));
    }

}
