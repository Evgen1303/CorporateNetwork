package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillConverterService;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee_skills")
public class EmployeeSkillController {

    private EmployeeSkillQueryService employeeSkillQueryService;
    private EmployeeSkillConverterService employeeSkillConverterService;

    @Autowired
    public EmployeeSkillController(EmployeeSkillQueryService employeeSkillQueryService,
                                   EmployeeSkillConverterService employeeSkillConverterService) {
        this.employeeSkillQueryService = employeeSkillQueryService;
        this.employeeSkillConverterService = employeeSkillConverterService;
    }

    @GetMapping
    public Iterable<EmployeeSkill> getAllEmployeeSkills() {
        return employeeSkillQueryService.getAllEmployeeSkills();
    }

    @GetMapping("/{id}")
    public EmployeeSkill getEmployeeSkills(@PathVariable Long id) {
        return employeeSkillQueryService.getEmployeeSkillById(id);
    }

    @PostMapping
    public EmployeeSkill createSkill(@RequestBody EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = employeeSkillConverterService.convertToEntity(employeeSkillDTO);
        employeeSkillQueryService.saveEmployeeSkill(employeeSkill);
        return employeeSkill;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Skill> deleteEmployeeSkill(@PathVariable Long id) {
        employeeSkillQueryService.deleteEmployeeSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkill> putSkill(@PathVariable Long id, @RequestBody EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = employeeSkillConverterService.convertToEntity(employeeSkillDTO);
        employeeSkill.setId(id);
        employeeSkillQueryService.updateEmployeeSkill(employeeSkill);
        return ResponseEntity.ok(employeeSkill);
    }

    //TODO PATCH
    /*@PatchMapping("/{id}")
    public ResponseEntity<EmployeeSkill> patchEmployeeSkill(@PathVariable Long id,
                                                            @RequestBody EmployeeSkill employeeSkill) {


        return ResponseEntity.ok(employeeSkill);
    }*/

}
