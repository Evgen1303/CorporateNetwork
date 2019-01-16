package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.service.SkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee_skills")
public class SkillController {

    private SkillQueryService skillQueryService;

    @Autowired
    public SkillController(SkillQueryService skillQueryService) {
        this.skillQueryService = skillQueryService;
    }

    @GetMapping
    public Iterable<EmployeeSkill> getAllEmployeeSkills() {
        return skillQueryService.getAllEmployeeSkills();
    }

    @GetMapping("skill_list")
    public Iterable<Skill> getAllSkills() {
        return skillQueryService.getAllSkills();
    }

    @GetMapping("e/{employeeId}")
    public EmployeeSkill getEmployeeSkills(@PathVariable Long employeeId) {
        return skillQueryService.getEmployeeSkillById(employeeId);
    }

    @GetMapping("s/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillQueryService.getSkillById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        skillQueryService.saveSkill(skill);
        return skill;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable Long id) {
        skillQueryService.deleteSkillById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Skill> updateSkillPut(@RequestBody Skill skill) {
        skillQueryService.updateSkill(skill);
        return ResponseEntity.ok(skill);
    }

}
