package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.dto.SkillDTO;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        if (!skill.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return skill.get();
    }

    public Iterable<Skill> getAllSkills() {

        Iterable<Skill> skills = skillRepository.findAll();
        if (!skills.iterator().hasNext()) {
            throw new ResourceNotFoundException();
        }
        return skills;
    }

    public Page<Skill> getPage(Pageable pageable) {
        return skillRepository.findAll(pageable);
    }

    public Skill saveSkill(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

   /* public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }*/

    public Skill updateSkill(Long id, SkillDTO skillDTO) {
        Skill skill = skillConverterService.convertToEntity(skillDTO);
        skill.setId(id);
        skillRepository.save(skill);
        return skill;
    }

}
