package co.norse.hr.mainservice.repos;

import co.norse.hr.mainservice.entity.EmployeeProject;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface EmployeeProjectRepo extends CrudRepository<EmployeeProject, Integer> {
    List<EmployeeProject> findById(int id);
    List<EmployeeProject> deleteById(int id);
    }
