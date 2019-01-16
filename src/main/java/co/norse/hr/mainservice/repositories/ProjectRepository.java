package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

