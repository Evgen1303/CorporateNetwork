package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.repositories.SkillRepository;
import javassist.NotFoundException;
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

    public Skill getSkillById(Long id) throws NotFoundException {

        Optional<Skill> skill = skillRepository.findById(id);

        if (!skill.isPresent()) {
            throw new NotFoundException("Skill not found");
        }
        return skill.get();
    }

    public Iterable<Skill> getAllSkills() throws NotFoundException {

        Iterable<Skill> skills = skillRepository.findAll();

        if (!skills.iterator().hasNext()) {
            throw new NotFoundException("Skill not found");
        }
        return skills;
    }

    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public void deleteSkillById(Long id) {
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
