package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.repositories.EmployeeSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillCommandService {

    private EmployeeSkillRepository employeeSkillRepository;
    private EmployeeSkillConverterService employeeSkillConverterService;

    @Autowired
    public EmployeeSkillCommandService(EmployeeSkillRepository employeeSkillRepository,
                                     EmployeeSkillConverterService employeeSkillConverterService) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeSkillConverterService = employeeSkillConverterService;
    }

    public EmployeeSkill saveEmployeeSkill(EmployeeSkill employeeSkill) {
        return employeeSkillRepository.saveAndFlush(employeeSkill);
    }

    public void deleteEmployeeSkill(Long id) {
        employeeSkillRepository.deleteById(id);
    }

    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill employeeSkill) {
        employeeSkill.setId(id);
        employeeSkillRepository.saveAndFlush(employeeSkill);
        return employeeSkill;
    }

    /*public void patchEmployeeSkill(Long id, EmployeeSkillDTO employeeSkillDTO) {
        employeeSkillDTO.setId(id);
        EmployeeSkillDTO oldEmployeeSkill1DTO = employeeSkillConverterService.convertToDTO(this.getEmployeeSkillById(id));
        if (employeeSkillDTO.getEmployeeId() == null) {
            employeeSkillDTO.setEmployeeId(oldEmployeeSkill1DTO.getEmployeeId());
        }

        if (employeeSkillDTO.getSkillId() == null) {
            employeeSkillDTO.setSkillId(oldEmployeeSkill1DTO.getSkillId());
        }

        if (employeeSkillDTO.getLevel() == 0) {
            employeeSkillDTO.setLevel(oldEmployeeSkill1DTO.getLevel());
        }
        this.saveEmployeeSkill(employeeSkillDTO);
    }*/


}
