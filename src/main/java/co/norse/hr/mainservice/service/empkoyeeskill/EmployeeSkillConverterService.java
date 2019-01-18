package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.service.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillConverterService {

    private EmployeeSkillQueryService employeeSkillQueryService;
    private EmployeeQueryService employeeQueryService;

    @Autowired
    public EmployeeSkillConverterService(EmployeeSkillQueryService employeeSkillQueryService, EmployeeQueryService employeeQueryService) {
        this.employeeSkillQueryService = employeeSkillQueryService;
        this.employeeQueryService = employeeQueryService;
    }

    public EmployeeSkillDTO convertToDTO(EmployeeSkill employeeSkill) {
        EmployeeSkillDTO employeeSkillDTO = new EmployeeSkillDTO();
        employeeSkillDTO.setEmployeeId(employeeSkill.getEmployee().getId());
        employeeSkillDTO.setSkillId(employeeSkill.getSkill().getId());
        employeeSkillDTO.setLevel(employeeSkill.getLevel());
        return employeeSkillDTO;
    }

    public EmployeeSkill convertToEntity(EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployee(employeeQueryService.getEmployeeById(employeeSkillDTO.getEmployeeId()));
        employeeSkill.setSkill(employeeSkillQueryService.getSkillById(employeeSkillDTO.getSkillId()));
        employeeSkill.setLevel(employeeSkillDTO.getLevel());
        return employeeSkill;
    }

}
