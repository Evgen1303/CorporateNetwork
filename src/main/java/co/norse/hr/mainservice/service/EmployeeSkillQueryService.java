package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.entity.Skill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeSkillRepository;
import co.norse.hr.mainservice.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeSkillQueryService {

    private SkillRepository skillRepository;
    private EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    public EmployeeSkillQueryService(SkillRepository skillRepository,
                                     EmployeeSkillRepository employeeSkillRepository) {
        this.skillRepository = skillRepository;
        this.employeeSkillRepository = employeeSkillRepository;
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

    public void saveEmployeeSkill(EmployeeSkill employeeSkill) {
        employeeSkillRepository.save(employeeSkill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public void deleteEmployeeSkill(Long id) {
        employeeSkillRepository.deleteById(id);
    }

    public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }

    public void deleteEmployeeSkill(EmployeeSkill employeeSkill) {
        employeeSkillRepository.delete(employeeSkill);
    }

    public void deleteAllSkill() {
        skillRepository.deleteAll();
    }

    public void deleteAllEmployeeSkill() {
        employeeSkillRepository.deleteAll();
    }

    public void updateSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public void updateEmployeeSkill(EmployeeSkill employeeSkill) {
        employeeSkillRepository.save(employeeSkill);
    }
}
