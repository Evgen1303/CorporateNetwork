package co.norse.hr.mainservice.service.company;

import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CompanyQueryService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyQueryService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findOneOrThrowException(Long id) {
        return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
