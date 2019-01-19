package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
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
    private EmployeeSkillConverterService employeeSkillConverterService;

    @Autowired
    public EmployeeSkillQueryService(SkillRepository skillRepository,
                                     EmployeeSkillRepository employeeSkillRepository,
                                     EmployeeSkillConverterService employeeSkillConverterService) {
        this.skillRepository = skillRepository;
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeSkillConverterService = employeeSkillConverterService;
    }

    public EmployeeSkill getEmployeeSkillById(Long id) {

        Optional<EmployeeSkill> employeeSkill = employeeSkillRepository.findById(id);
        return employeeSkill.orElseThrow(ResourceNotFoundException::new);
    }

    public Iterable<EmployeeSkill> getAllEmployeeSkills() {

        Iterable<EmployeeSkill> employeeSkills = employeeSkillRepository.findAll();

        if (!employeeSkills.iterator().hasNext()) {
            throw new ResourceNotFoundException();
        }
        return employeeSkills;
    }

    public void saveEmployeeSkill(EmployeeSkill employeeSkill) {
        employeeSkillRepository.save(employeeSkill);
    }

    public void deleteEmployeeSkill(Long id) {
        employeeSkillRepository.deleteById(id);
    }

    public void deleteEmployeeSkill(EmployeeSkill employeeSkill) {
        employeeSkillRepository.delete(employeeSkill);
    }

    public void deleteAllEmployeeSkill() {
        employeeSkillRepository.deleteAll();
    }

    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = employeeSkillConverterService.convertToEntity(employeeSkillDTO);
        employeeSkill.setId(id);
        employeeSkillRepository.save(employeeSkill);
        return employeeSkill;
    }
    //TODO PATCH
    /*public void patchEmployeeSkill(Long id, EmployeeSkill employeeSkill){
        EmployeeSkill oldEmployeeSkill1 = this.getEmployeeSkillById(id);

    }*/

}
