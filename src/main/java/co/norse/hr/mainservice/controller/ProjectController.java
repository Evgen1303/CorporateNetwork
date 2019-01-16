package co.norse.hr.mainservice.controller;


import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.service.ProjectQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProjectController {
    private ProjectQueryService projectQueryService;

    @Autowired
    public ProjectController(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
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
    public Project createProject(@RequestBody Project project) {
        projectQueryService.saveProject(project);
        return project;
    }

    @DeleteMapping("projects/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        projectQueryService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("projects")
    public ResponseEntity<Project> updateProjectPut(@RequestParam Long id,
                                                    @RequestBody Project project) {
        project.setId(id);
        projectQueryService.updateProject(project);
        return ResponseEntity.ok(project);
    }

    //////////// employeeprojects
    @GetMapping("employeeprojects")
    public Iterable<EmployeeProject> getAllEmployeeProjects() {
        return projectQueryService.getAllEmployeeProjects();
    }

    @GetMapping("employeeprojects/{id}")
    public EmployeeProject getEmployeeProject(@PathVariable Long id) {
        return projectQueryService.getEmployeeProjectById(id);
    }

    @PostMapping("employeeprojects")
    public EmployeeProject createEmployeeProject(@RequestBody EmployeeProject employeeProject) {
        projectQueryService.saveEmployeeProject(employeeProject);
        return employeeProject;
    }

    @DeleteMapping("employeeproject/{id}")
    public ResponseEntity deleteEmployeeProject(@PathVariable Long id) {
        projectQueryService.deleteEmployeeProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("employeeprojects")
    public ResponseEntity<EmployeeProject> updateEmployeeProjectPut(@RequestParam Long id,
                                                                    @RequestBody EmployeeProject employeeProject) {
        employeeProject.setId(id);
        projectQueryService.updateEmployeeProject(employeeProject);
        return ResponseEntity.ok(employeeProject);
    }

}
