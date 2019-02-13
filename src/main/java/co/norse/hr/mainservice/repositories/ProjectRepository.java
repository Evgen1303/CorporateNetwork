package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByEmployeeProjects_EmployeeId(Long id);
}
