package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.dto.SkillDTO;
import co.norse.hr.mainservice.entity.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SkillConverterService {

    private ModelMapper modelMapper = new ModelMapper();

    public SkillDTO convertToDTO(Skill skill) {
        return modelMapper.map(skill, SkillDTO.class);
    }

    public Skill convertToEntity(SkillDTO skillDTO) {
        return modelMapper.map(skillDTO, Skill.class);
    }
}
