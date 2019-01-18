package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeRepository;
import co.norse.hr.mainservice.repositories.EmployeeSkillRepository;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillQueryService {

    private SkillRepository skillRepository;
    private EmployeeSkillRepository employeeSkillRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public SkillQueryService(SkillRepository skillRepository, EmployeeRepository employeeRepository,
                             EmployeeSkillRepository employeeSkillRepository) {
        this.skillRepository = skillRepository;
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
    }

    public Skill getSkillById(Long id) {

        Optional<Skill> skill = skillRepository.findById(id);

        if (!skill.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return skill.get();
    }

    public EmployeeSkill getEmployeeSkillById(Long id) {

        Optional<EmployeeSkill> employeeSkill = employeeSkillRepository.findById(id);

        if (!employeeSkill.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return employeeSkill.get();
    }

    public Iterable<Skill> getAllSkills() {

        Iterable<Skill> skills = skillRepository.findAll();

        if (!skills.iterator().hasNext()) {
            throw new ResourceNotFoundException();
        }
        return skills;
    }

    public Iterable<EmployeeSkill> getAllEmployeeSkills() {

        Iterable<EmployeeSkill> employeeSkills = employeeSkillRepository.findAll();

        if (!employeeSkills.iterator().hasNext()) {
            throw new ResourceNotFoundException();
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
