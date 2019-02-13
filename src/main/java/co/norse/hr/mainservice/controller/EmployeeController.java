package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.dto.FilterDto;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.employee.EmployeeConverterService;
import co.norse.hr.mainservice.service.employee.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public final class EmployeeController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "firstName";

    private EmployeeQueryService employeeQueryService;
    private EmployeeConverterService employeeConverterService;

    @Autowired
    public EmployeeController(EmployeeQueryService employeeQueryService, EmployeeConverterService employeeConverterService) {
        this.employeeQueryService = employeeQueryService;
        this.employeeConverterService = employeeConverterService;
    }

    @GetMapping
    public Page<Employee> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return employeeQueryService.getPage(pageable);
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

    @GetMapping("/filter")
    public Page<Employee> getPageTest(@PageableDefault(size = DEFAULT_PAGE_SIZE)
                                      @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                                              Pageable pageable, @RequestBody FilterDto filterDto) {
        return employeeQueryService.getAllEmployeebyFields(pageable, filterDto);
    }

    @GetMapping("/find/{name}")
    public List<Employee> getEmployeeByNameOrLastname(@PathVariable String name) {
        return employeeQueryService.findByNameOrLastname(name);
    }
}
