package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillConverterService;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee-skills")
public class EmployeeSkillController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "level";

    private EmployeeSkillQueryService employeeSkillQueryService;
    private EmployeeSkillConverterService employeeSkillConverterService;

    @Autowired
    public EmployeeSkillController(EmployeeSkillQueryService employeeSkillQueryService,
                                   EmployeeSkillConverterService employeeSkillConverterService) {
        this.employeeSkillQueryService = employeeSkillQueryService;
        this.employeeSkillConverterService = employeeSkillConverterService;
    }

    @GetMapping("get-all")
    public Iterable<EmployeeSkill> getAllEmployeeSkills() {
        return employeeSkillQueryService.getAllEmployeeSkills();
    }

    @GetMapping
    public Page<EmployeeSkill> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return employeeSkillQueryService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public EmployeeSkill getEmployeeSkills(@PathVariable Long id) {
        return employeeSkillQueryService.getEmployeeSkillById(id);
    }

    @PostMapping
    public EmployeeSkill createSkill(@RequestBody EmployeeSkillDTO employeeSkillDTO) {
        return employeeSkillQueryService.saveEmployeeSkill(employeeSkillConverterService.convertToEntity(employeeSkillDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Skill> deleteEmployeeSkill(@PathVariable Long id) {
        employeeSkillQueryService.deleteEmployeeSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkill> putSkill(@PathVariable Long id, @RequestBody EmployeeSkillDTO employeeSkillDTO) {
        return ResponseEntity.ok(employeeSkillQueryService.updateEmployeeSkill(id, employeeSkillDTO));
    }

    //TODO PATCH
    /*@PatchMapping("/{id}")
    public ResponseEntity<EmployeeSkill> patchEmployeeSkill(@PathVariable Long id,
                                                            @RequestBody EmployeeSkill employeeSkill) {


        return ResponseEntity.ok(employeeSkill);
    }*/

}
