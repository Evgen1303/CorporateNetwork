package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.dto.SkillDTO;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillQueryService {

    private SkillRepository skillRepository;
    private SkillConverterService skillConverterService;

    @Autowired
    public SkillQueryService(SkillRepository skillRepository, SkillConverterService skillConverterService) {
        this.skillRepository = skillRepository;
        this.skillConverterService = skillConverterService;
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

    public Skill updateSkill(Long id, SkillDTO skillDTO) {
        Skill skill = skillConverterService.convertToEntity(skillDTO);
        skill.setId(id);
        skillRepository.save(skill);
        return  skill;
    }

}
