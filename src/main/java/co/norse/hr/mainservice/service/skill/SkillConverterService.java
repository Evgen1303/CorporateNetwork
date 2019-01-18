package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.dto.SkillDTO;
import co.norse.hr.mainservice.entity.Skill;
import org.springframework.stereotype.Service;

@Service
public class SkillConverterService {

    public SkillDTO convertToDTO(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setName(skill.getName());

        return skillDTO;
    }

    public Skill convertToEntity(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setName(skillDTO.getName());

        return skill;
    }

}
