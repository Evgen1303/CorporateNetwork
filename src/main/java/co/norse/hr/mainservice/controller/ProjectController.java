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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "level";

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

    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectQueryService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectQueryService.getProjectById(id);
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
    public ResponseEntity<Project> updateProjectPut(@PathVariable Long id,
                                                    @RequestBody ProjectDto projectDto) {
        Project project = projectConverterService.convertToEntity(projectDto);
        projectQueryService.updateProject(project);
        return ResponseEntity.ok(project);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        projectQueryService.patchProject(projectDto, id);
        return ResponseEntity.ok(projectConverterService.convertToEntity(projectDto));
    }
}
