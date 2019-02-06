package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.service.employee.EmployeeQueryService;
import co.norse.hr.mainservice.service.skill.SkillQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillConverterService {

    private EmployeeQueryService employeeQueryService;
    private SkillQueryService skillQueryService;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public EmployeeSkillConverterService(EmployeeQueryService employeeQueryService,
                                         SkillQueryService skillQueryService) {
        this.employeeQueryService = employeeQueryService;
        this.skillQueryService = skillQueryService;
    }
    public EmployeeSkillDTO convertToDTO(EmployeeSkill employeeSkill) {
        return modelMapper.map(employeeSkill, EmployeeSkillDTO.class);
    }
    public EmployeeSkill convertToEntity(EmployeeSkillDTO employeeSkillDTO) {
        EmployeeSkill employeeSkill = modelMapper.map(employeeSkillDTO, EmployeeSkill.class);
        employeeSkill.setEmployee(employeeQueryService.getEmployeeById(employeeSkillDTO.getEmployeeId()));
        employeeSkill.setSkill(skillQueryService.getSkillById(employeeSkillDTO.getSkillId()));
        return employeeSkill;
    }
}
