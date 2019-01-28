package co.norse.hr.mainservice.service.empkoyeeskill;

import co.norse.hr.mainservice.dto.EmployeeSkillDTO;
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
    private EmployeeSkillConverterService employeeSkillConverterService;

    @Autowired
    public EmployeeSkillQueryService(EmployeeSkillRepository employeeSkillRepository,
                                     EmployeeSkillConverterService employeeSkillConverterService) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeSkillConverterService = employeeSkillConverterService;
    }

    public EmployeeSkill getEmployeeSkillById(Long id) {
        return employeeSkillRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<EmployeeSkill> getAllSkillsByEmployeeId(Long id){
        return employeeSkillRepository.findEmployeeSkillsByEmployeeId(id);
    }

    public List<EmployeeSkill> getAllEmployeesBySkillId(Long id){
        return employeeSkillRepository.findEmployeeSkillsBySkillId(id);
    }

    public Page<EmployeeSkill> getPage(Pageable pageable) {
        return employeeSkillRepository.findAll(pageable);
    }

    public EmployeeSkill saveEmployeeSkill(EmployeeSkillDTO employeeSkillDTO) {
        return employeeSkillRepository.save(employeeSkillConverterService.convertToEntity(employeeSkillDTO));
    }

    public void deleteEmployeeSkill(Long id) {
        employeeSkillRepository.deleteById(id);
    }

    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkillDTO employeeSkillDTO) {
        employeeSkillDTO.setId(id);
        EmployeeSkill employeeSkill = employeeSkillConverterService.convertToEntity(employeeSkillDTO);
        employeeSkillRepository.save(employeeSkill);
        return employeeSkill;
    }

    public void patchEmployeeSkill(Long id, EmployeeSkillDTO employeeSkillDTO) {
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
    }

}
