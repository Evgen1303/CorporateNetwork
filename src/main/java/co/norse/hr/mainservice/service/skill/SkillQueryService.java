package co.norse.hr.mainservice.service.skill;

import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SkillQueryService {

    private SkillRepository skillRepository;

    @Autowired
    public SkillQueryService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Skill> getPage(Pageable pageable) {
        return skillRepository.findAll(pageable);
    }

}
