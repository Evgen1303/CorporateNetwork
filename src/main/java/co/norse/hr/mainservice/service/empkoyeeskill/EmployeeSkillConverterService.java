package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.service.employee.EmployeeQueryService;
import co.norse.hr.mainservice.service.skill.SkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillConverterService {

    private EmployeeQueryService employeeQueryService;
    private SkillQueryService skillQueryService;

    @Autowired
    public EmployeeSkillConverterService(EmployeeQueryService employeeQueryService,
                                         SkillQueryService skillQueryService) {
        this.employeeQueryService = employeeQueryService;
        this.skillQueryService = skillQueryService;
    }

    public EmployeeSkillDTO convertToDTO(EmployeeSkill employeeSkill) {
        EmployeeSkillDTO employeeSkillDTO = new EmployeeSkillDTO();
        employeeSkillDTO.setId(employeeSkill.getId());
        employeeSkillDTO.setEmployeeId(employeeSkill.getEmployee().getId());
        employeeSkillDTO.setSkillId(employeeSkill.getSkill().getId());
        employeeSkillDTO.setLevel(employeeSkill.getLevel());
        return employeeSkillDTO;
    }

    public EmployeeSkill convertToEntity(EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setId(employeeSkillDTO.getId());
        employeeSkill.setEmployee(employeeQueryService.getEmployeeById(employeeSkillDTO.getEmployeeId()));
        employeeSkill.setSkill(skillQueryService.getSkillById(employeeSkillDTO.getSkillId()));
        employeeSkill.setLevel(employeeSkillDTO.getLevel());
        return employeeSkill;
    }

}
