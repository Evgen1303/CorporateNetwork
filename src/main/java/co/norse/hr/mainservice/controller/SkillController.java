package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.SkillDTO;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.service.skill.SkillConverterService;
import co.norse.hr.mainservice.service.skill.SkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skills")
public class SkillController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";

    private SkillQueryService skillQueryService;

    @Autowired
    public SkillController(SkillQueryService skillQueryService) {
        this.skillQueryService = skillQueryService;
    }
    
    @GetMapping
    public Page<Skill> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return skillQueryService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillQueryService.getSkillById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody SkillDTO skillDTO) {
        return skillQueryService.saveSkill(skillDTO);
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
