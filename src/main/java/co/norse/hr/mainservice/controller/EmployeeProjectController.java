package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.service.employeeproject.EmployeeProjectConverterService;
import co.norse.hr.mainservice.service.employeeproject.EmployeeProjectQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employeeprojects")
public class EmployeeProjectController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "level";

    private EmployeeProjectQueryService employeeProjectQueryService;
    private EmployeeProjectConverterService employeeProjectConverterService;

    @Autowired
    public EmployeeProjectController(EmployeeProjectQueryService employeeProjectQueryService, EmployeeProjectConverterService employeeProjectConverterService) {
        this.employeeProjectQueryService = employeeProjectQueryService;
        this.employeeProjectConverterService = employeeProjectConverterService;
    }

    @GetMapping("/all")
    public Iterable<EmployeeProject> getAllEmployeeProjects() {
        return employeeProjectQueryService.getAllEmployeeProjects();
    }

    @GetMapping
    public Page<EmployeeProject> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return employeeProjectQueryService.getPage(pageable);
    }


    @GetMapping("/{id}")
    public EmployeeProject getEmployeeProject(@PathVariable Long id) {
        return employeeProjectQueryService.getEmployeeProjectById(id);
    }

    @PostMapping
    public EmployeeProject createEmployeeProject(@RequestBody EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = employeeProjectConverterService.convertToEntity(employeeProjectDto);
        employeeProjectQueryService.saveEmployeeProject(employeeProject);
        return employeeProject;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeProject(@PathVariable Long id) {
        employeeProjectQueryService.deleteEmployeeProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<EmployeeProject> updateEmployeeProjectPut(@RequestParam Long id,
                                                                    @RequestBody EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = employeeProjectConverterService.convertToEntity(employeeProjectDto);
        employeeProjectQueryService.updateEmployeeProject(employeeProject);
        return ResponseEntity.ok(employeeProject);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeProject> patchProject(@PathVariable Long id, @RequestBody EmployeeProjectDto employeeProjectDto) {
        employeeProjectQueryService.patchEmployeeProject(employeeProjectDto, id);
        return ResponseEntity.ok(employeeProjectConverterService.convertToEntity(employeeProjectDto));
    }
}
