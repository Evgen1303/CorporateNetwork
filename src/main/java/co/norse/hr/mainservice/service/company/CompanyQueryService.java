package co.norse.hr.mainservice.service.company;

import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryService {

    private CompanyRepository companyRepository;
    private CompanyConvertService companyConvertService;

    @Autowired
    public CompanyQueryService(CompanyRepository companyRepository, CompanyConvertService companyConvertService) {
        this.companyConvertService = companyConvertService;
        this.companyRepository = companyRepository;
    }

    public Company findOneOrThrowException(Long id) {
        return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Company getCompanyById(Long id) {
        Optional<Company> result = companyRepository.findById(id);
        return result.orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Company> getPage(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();

    }
}