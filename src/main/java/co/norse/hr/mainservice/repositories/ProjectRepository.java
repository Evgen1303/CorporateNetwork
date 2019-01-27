package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM project p join employee_project ep join employee e on p.id = ep.project_ld and e.id = ep.employee_ld WHERE e.id = ?1", nativeQuery = true)
    List<Project> findProjectsById(Long id);
}

