package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillQueryService {

    private EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    public EmployeeSkillQueryService(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    public EmployeeSkill getEmployeeSkillById(Long id) {
        return employeeSkillRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<EmployeeSkill> getAllSkillsByEmployeeId(Long id) {
        return employeeSkillRepository.findEmployeeSkillsByEmployeeId(id);
    }

    public List<EmployeeSkill> getAllEmployeesBySkillId(Long id) {
        return employeeSkillRepository.findEmployeeSkillsBySkillId(id);
    }

    public Page<EmployeeSkill> getPage(Pageable pageable) {
        return employeeSkillRepository.findAll(pageable);
    }
}
