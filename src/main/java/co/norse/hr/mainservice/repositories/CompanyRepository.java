package co.norse.hr.mainservice.repositories;

import co.norse.hr.mainservice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
