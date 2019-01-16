package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeDto;
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

    //TODO: DTO
    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeQueryService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return employeeQueryService.getEmployeeById(id).convertToDto();
    }

    @PostMapping
    public EmployeeDto createEmployee (@RequestBody EmployeeDto employeeDto) {
        employeeQueryService.saveEmployee(employeeDto.convertToEntity());
        return employeeDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee (@PathVariable Long id) {
        employeeQueryService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> putEmployee (@PathVariable Long id,
                                                                     @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeDto.convertToEntity();
        employeeQueryService.updateEmployee(employee);
        return ResponseEntity.ok(employee.convertToDto());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> patchEmployee (@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeQueryService.patchEmployee(employeeDto, id);
        return ResponseEntity.ok(employeeDto);
    }

}
