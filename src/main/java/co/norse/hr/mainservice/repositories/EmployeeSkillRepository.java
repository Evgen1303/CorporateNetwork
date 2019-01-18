package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
}
