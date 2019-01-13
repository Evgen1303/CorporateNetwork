package co.norse.hr.mainservice.repos;

import co.norse.hr.mainservice.entity.Project;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface ProjectRepo extends CrudRepository<Project, Integer> {
   List<Project> findById(int id);
   List<Project> deleteById(int id);
  }
