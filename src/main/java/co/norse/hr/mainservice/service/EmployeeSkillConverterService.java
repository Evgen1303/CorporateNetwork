package co.norse.hr.mainservice.service;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillConverterService {

    public EmployeeSkillDTO convertToDTO(EmployeeSkill employeeSkill) {
        EmployeeSkillDTO employeeSkillDTO = new EmployeeSkillDTO();
        employeeSkillDTO.setEmployeeId(employeeSkill.getEmployee().getId());
        employeeSkillDTO.setSkillId(employeeSkill.getSkill().getId());
        employeeSkillDTO.setLevel(employeeSkill.getLevel());
        return employeeSkillDTO;
    }

    public EmployeeSkill convertToEntity(EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        //employeeSkill.setEmployee(employeeSkillDTO.getEmployeeId());
        //employeeSkill.setSkill(employeeSkillDTO.getSkillId());
        employeeSkill.setLevel(employeeSkillDTO.getLevel());
        return employeeSkill;
    }

}
