package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeProjectDto;
import co.norse.hr.mainservice.dto.ProjectDto;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.service.employeeproject.EmployeeProjectConverterService;
import co.norse.hr.mainservice.service.employeeproject.EmployeeProjectQueryService;
import co.norse.hr.mainservice.service.project.ProjectConverterService;
import co.norse.hr.mainservice.service.project.ProjectQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProjectController {
    private ProjectQueryService projectQueryService;
    private EmployeeProjectQueryService employeeProjectQueryService;
    private ProjectConverterService projectConverterService;
    private EmployeeProjectConverterService employeeProjectConverterService;

    @Autowired
    public ProjectController(ProjectQueryService projectQueryService, ProjectConverterService projectConverterService) {
        this.projectQueryService = projectQueryService;
        this.projectConverterService = projectConverterService;
    }

    @GetMapping("projects")
    public List<Project> getAllProjects() {
        return projectQueryService.getAllProjects();
    }

    @GetMapping("projects/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectQueryService.getProjectById(id);
    }

    @PostMapping("projects")
    public Project createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectConverterService.convertToEntity(projectDto);
        projectQueryService.saveProject(project);
        return project;
    }

    @DeleteMapping("projects/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        projectQueryService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("projects/{id}")
    public ResponseEntity<Project> updateProjectPut(@PathVariable Long id,
                                                    @RequestBody ProjectDto projectDto) {
        Project project = projectConverterService.convertToEntity(projectDto);
        projectQueryService.updateProject(project);
        return ResponseEntity.ok(project);
    }

    @PatchMapping("projects/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        projectQueryService.patchProject(projectDto, id);
        return ResponseEntity.ok(projectConverterService.convertToEntity(projectDto));
    }

    //////////// employeeprojects
    @GetMapping("employeeprojects")
    public Iterable<EmployeeProject> getAllEmployeeProjects() {
        return employeeProjectQueryService.getAllEmployeeProjects();
    }

    @GetMapping("employeeprojects/{id}")
    public EmployeeProject getEmployeeProject(@PathVariable Long id) {
        return employeeProjectQueryService.getEmployeeProjectById(id);
    }

    @PostMapping("employeeprojects")
    public EmployeeProject createEmployeeProject(@RequestBody EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = employeeProjectConverterService.convertToEntity(employeeProjectDto);
        employeeProjectQueryService.saveEmployeeProject(employeeProject);
        return employeeProject;
    }

    @DeleteMapping("employeeproject/{id}")
    public ResponseEntity deleteEmployeeProject(@PathVariable Long id) {
        employeeProjectQueryService.deleteEmployeeProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("employeeprojects")
    public ResponseEntity<EmployeeProject> updateEmployeeProjectPut(@RequestParam Long id,
                                                                    @RequestBody EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = employeeProjectConverterService.convertToEntity(employeeProjectDto);
        employeeProjectQueryService.updateEmployeeProject(employeeProject);
        return ResponseEntity.ok(employeeProject);
    }

    @PatchMapping("employeeprojects/{id}")
    public ResponseEntity<EmployeeProject> patchProject(@PathVariable Long id, @RequestBody EmployeeProjectDto employeeProjectDto) {
        employeeProjectQueryService.patchEmployeeProject(employeeProjectDto, id);
        return ResponseEntity.ok(employeeProjectConverterService.convertToEntity(employeeProjectDto));
    }


}
