package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.ProjectDto;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.service.project.ProjectConverterService;
import co.norse.hr.mainservice.service.project.ProjectQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";

    private ProjectQueryService projectQueryService;
    private ProjectConverterService projectConverterService;

    @Autowired
    public ProjectController(ProjectQueryService projectQueryService, ProjectConverterService projectConverterService) {
        this.projectQueryService = projectQueryService;
        this.projectConverterService = projectConverterService;
    }

    @GetMapping
    public Page<Project> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return projectQueryService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectQueryService.getProjectById(id);
    }

    @GetMapping("/employee/{id}")
    public List<Project> getProjectTest(@PathVariable Long id) {
        return projectQueryService.getProjectByEmployeeId(id);
    }

    @PostMapping
    public Project createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectConverterService.convertToEntity(projectDto);
        projectQueryService.saveProject(project);
        return project;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        projectQueryService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Project> putProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectQueryService.updateProject(id, projectDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectQueryService.patchProject(id, projectDto));
    }
}
