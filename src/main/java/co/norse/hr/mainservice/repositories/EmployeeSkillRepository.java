package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findAllById(Long id);
    EmployeeSkill findByEmployeeId(Long id);
    void deleteById(Long id);

}
