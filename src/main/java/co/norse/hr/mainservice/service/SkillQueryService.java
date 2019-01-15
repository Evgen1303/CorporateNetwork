package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.repositories.EmployeeSkillRepository;
import co.norse.hr.mainservice.repositories.SkillRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillQueryService {

    private SkillRepository skillRepository;
    private EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    public SkillQueryService(SkillRepository skillRepository, EmployeeSkillRepository employeeSkillRepository) {
        this.skillRepository = skillRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    public Skill getSkillById(Long id) throws NotFoundException {

        Optional<Skill> skill = skillRepository.findById(id);

        if (!skill.isPresent()) {
            throw new NotFoundException("Skill not found");
        }
        return skill.get();
    }

    public EmployeeSkill getEmployeeSkillById(Long id) throws NotFoundException {

        Optional<EmployeeSkill> employeeSkill = employeeSkillRepository.findById(id);

        if (!employeeSkill.isPresent()) {
            throw new NotFoundException("EmployeeSkill not found");
        }
        return employeeSkill.get();
    }

    public Iterable<Skill> getAllSkills() throws NotFoundException {

        Iterable<Skill> skills = skillRepository.findAll();

        if (!skills.iterator().hasNext()) {
            throw new NotFoundException("Skill not found");
        }
        return skills;
    }
    public Iterable<EmployeeSkill> getAllEmployeeSkills() throws NotFoundException {

        Iterable<EmployeeSkill> employeeSkills = employeeSkillRepository.findAll();

        if (!employeeSkills.iterator().hasNext()) {
            throw new NotFoundException("Skill not found");
        }
        return employeeSkills;
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
