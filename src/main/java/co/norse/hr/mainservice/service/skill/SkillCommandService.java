package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillCommandService {

    private SkillRepository skillRepository;

    @Autowired
    public SkillCommandService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.saveAndFlush(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public Skill updateSkill(Long id, Skill skill) {
        skill.setId(id);
        skillRepository.saveAndFlush(skill);
        return skill;
    }

}
