package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillQueryService {

    private SkillRepository skillRepository;

    @Autowired
    public SkillQueryService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        return skill.orElseThrow(ResourceNotFoundException::new);
    }

    public Iterable<Skill> getAllSkills() {

        Iterable<Skill> skills = skillRepository.findAll();
        if (!skills.iterator().hasNext()) {
            throw new ResourceNotFoundException();
        }
        return skills;
    }

    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }

    public void deleteAllSkill() {
        skillRepository.deleteAll();
    }

    public void updateSkill(Skill skill) {
        skillRepository.save(skill);
    }

}
