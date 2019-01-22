package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillConverterService;
import co.norse.hr.mainservice.service.empkoyeeskill.EmployeeSkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee-skills")
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
        employeeSkillQueryService.saveEmployeeSkill(employeeSkillConverterService.convertToEntity(employeeSkillDTO));
        return employeeSkillConverterService.convertToEntity(employeeSkillDTO);
    }

    @DeleteMapping
    public ResponseEntity<EmployeeSkill> deleteEmployeeSkill(@RequestBody EmployeeSkill employeeSkill) {
        employeeSkillQueryService.deleteEmployeeSkill(employeeSkill);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeSkill> deleteEmployeeSkill(@PathVariable Long id) {
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
