package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findAllById(Long id);

    void deleteById(Long id);

}
