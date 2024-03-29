package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillCommandService;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillConverterService;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee-skills")
public class EmployeeSkillController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "level";

    private EmployeeSkillQueryService employeeSkillQueryService;
    private EmployeeSkillCommandService employeeSkillCommandService;
    private EmployeeSkillConverterService employeeSkillConverterService;

    @Autowired
    public EmployeeSkillController(EmployeeSkillQueryService employeeSkillQueryService,
                                   EmployeeSkillCommandService employeeSkillCommandService,
                                   EmployeeSkillConverterService employeeSkillConverterService) {
        this.employeeSkillQueryService = employeeSkillQueryService;
        this.employeeSkillCommandService = employeeSkillCommandService;
        this.employeeSkillConverterService = employeeSkillConverterService;
    }

    @GetMapping
    public Page<EmployeeSkill> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return employeeSkillQueryService.getPage(pageable);
    }

    @GetMapping("/employee/{id}")
    public List<EmployeeSkill> getAllSkillsByEmployeeId(@PathVariable Long id) {
        return employeeSkillQueryService.getAllSkillsByEmployeeId(id);
    }

    @GetMapping("/skill/{id}")
    public List<EmployeeSkill> getAllEmployeesBySkillId(@PathVariable Long id) {
        return employeeSkillQueryService.getAllEmployeesBySkillId(id);
    }


    @GetMapping("/{id}")
    public EmployeeSkill getEmployeeSkills(@PathVariable Long id) {
        return employeeSkillQueryService.getEmployeeSkillById(id);
    }

    @PostMapping
    public EmployeeSkill createSkill(@RequestBody EmployeeSkillDTO employeeSkillDTO) {
        return employeeSkillCommandService.saveEmployeeSkill(
                employeeSkillConverterService.convertToEntity(employeeSkillDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeSkill> deleteEmployeeSkill(@PathVariable Long id) {
        employeeSkillCommandService.deleteEmployeeSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkill> putSkill(@PathVariable Long id, @RequestBody EmployeeSkillDTO employeeSkillDTO) {
        return ResponseEntity.ok(employeeSkillCommandService.updateEmployeeSkill(id,
                employeeSkillConverterService.convertToEntity(employeeSkillDTO)));
    }


    /*@PatchMapping("/{id}")
    public ResponseEntity<EmployeeSkill> patchEmployeeSkill(@PathVariable Long id,
                                                            @RequestBody EmployeeSkillDTO employeeSkillDTO) {
        employeeSkillQueryService.patchEmployeeSkill(id, employeeSkillDTO);

        return ResponseEntity.ok(employeeSkillConverterService.convertToEntity(employeeSkillDTO));
    }*/

}
