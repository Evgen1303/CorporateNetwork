package co.norse.hr.mainservice.service.employee;

import co.norse.hr.mainservice.dto.EmployeeDto;
import co.norse.hr.mainservice.dto.FilterDto;
import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.entity.EmployeeSkill;
import co.norse.hr.mainservice.exception.EmployeeNotFoundException;
import co.norse.hr.mainservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeQueryService {
    private EmployeeRepository employeeRepository;
    private EmployeeConverterService employeeConverterService;

    @Autowired
    public EmployeeQueryService(EmployeeRepository employeeRepository, EmployeeConverterService employeeConverterService) {
        this.employeeRepository = employeeRepository;
        this.employeeConverterService = employeeConverterService;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElseThrow(EmployeeNotFoundException::new);
    }

    public void saveEmployee(@Valid Employee employee) {
        employeeRepository.save(employee);
    }

    public Iterable<Employee> getAllEmployees() {
        Iterable<Employee> result = employeeRepository.findAll();
        if (!result.iterator().hasNext()) {
            throw new EmployeeNotFoundException();
        }
        return result;
    }

    public Page<Employee> getPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    public void updateEmployee(@Valid Employee employee) {
        employeeRepository.save(employee);
    }

    public void patchEmployee(@Valid EmployeeDto employeeDto, Long id) {
        EmployeeDto oldEmployeeDto = employeeConverterService.convertToDto(this.getEmployeeById(id));
        if (employeeDto.getBirthday() == 0) {
            employeeDto.setBirthday(oldEmployeeDto.getBirthday());
        }
        if (employeeDto.getCompanyId() == 0) {
            employeeDto.setCompanyId(oldEmployeeDto.getCompanyId());
        }
        if (employeeDto.getDescription().length() == 0) {
            employeeDto.setDescription(oldEmployeeDto.getDescription());
        }
        if (employeeDto.getEmail().length() == 0) {
            employeeDto.setEmail(oldEmployeeDto.getEmail());
        }
        if (employeeDto.getFirstName().length() == 0) {
            employeeDto.setFirstName(oldEmployeeDto.getFirstName());
        }
        if (employeeDto.getLastName().length() == 0) {
            employeeDto.setLastName(oldEmployeeDto.getLastName());
        }
        if (employeeDto.getOfficeId() == 0) {
            employeeDto.setOfficeId(oldEmployeeDto.getOfficeId());
        }
        if (employeeDto.getPhone().length() == 0) {
            employeeDto.setPhone(oldEmployeeDto.getPhone());
        }
        if (employeeDto.getPosition().length() == 0) {
            employeeDto.setPosition(oldEmployeeDto.getPosition());
        }
        if (employeeDto.getRoomNumber().length() == 0) {
            employeeDto.setRoomNumber(oldEmployeeDto.getRoomNumber());
        }
        this.updateEmployee(employeeConverterService.convertToEntity(employeeDto));
    }

    public Employee findOneOrThrowException(Long id) {
        return employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Employee> getAllEmployeebyFields(Pageable pageable, FilterDto filterDto) {
        Specification<Employee> spec = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (filterDto.getOfficeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("office").get("id"), filterDto.getOfficeId())));
                }
                if (filterDto.getPosition() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("position"), filterDto.getPosition())));
                }
                if (filterDto.getCompanyId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("company").get("id"), filterDto.getCompanyId())));
                }
                if (filterDto.getBirthday() != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("birthday"), filterDto.getBirthday())));
                }
                if (filterDto.getRoomNumber() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("roomNumber"), filterDto.getRoomNumber())));
                }
                if (!filterDto.getProject().isEmpty()) {
                    for (String temp : filterDto.getProject()) {
                        Join<Employee, EmployeeProject> joinproject = root.join("employeeProjects", JoinType.INNER);
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(joinproject.get("project").get("name"), temp)));
                    }
                }
                if (!filterDto.getSkill().isEmpty()) {
                    for (String temp : filterDto.getProject()) {
                        Join<Employee, EmployeeSkill> joinskill = root.join("employeeSkills", JoinType.INNER);
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(joinskill.get("skill").get("id").as(String.class), temp)));
                    }
                }
                Predicate[] predicatesArray = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicatesArray));
            }
        };
        return employeeRepository.findAll(spec, pageable);
    }

    public List<Employee> findByNameOrLastname(String name) {

        return employeeRepository.findByFirstNameContainingOrLastNameContaining(name, name);

    }
}
