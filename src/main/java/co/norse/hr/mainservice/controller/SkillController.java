package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.SkillDTO;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.service.skill.SkillConverterService;
import co.norse.hr.mainservice.service.skill.SkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skills")
public class SkillController {

    private SkillQueryService skillQueryService;
    private SkillConverterService skillConverterService;

    @Autowired
    public SkillController(SkillQueryService skillQueryService, SkillConverterService skillConverterService) {
        this.skillQueryService = skillQueryService;
        this.skillConverterService = skillConverterService;
    }

    @GetMapping
    public Iterable<Skill> getAllSkills() {
        return skillQueryService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillQueryService.getSkillById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody SkillDTO skillDTO) {
        skillQueryService.saveSkill(skillConverterService.convertToEntity(skillDTO));
        return skillConverterService.convertToEntity(skillDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable Long id) {
        skillQueryService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> putSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillQueryService.updateSkill(id, skillDTO));
    }
}
