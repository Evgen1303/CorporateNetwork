package co.norse.hr.mainservice.service.company;

import co.norse.hr.mainservice.dto.CompanyDTO;
import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }

    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    public Company updateCompany(CompanyDTO companyDTO, Long id) {
        Company company = companyConvertService.convertToEntity(companyDTO);
        company.setId(id);
        this.saveCompany(company);
        return company;
    }

    public Page<Company> getPage(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Iterable<Company> getAllCompanies() {
        Iterable<Company> result = companyRepository.findAll();
        if (!result.iterator().hasNext()) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    public void patchCompany(CompanyDTO companyDTO, Long id) {
        CompanyDTO oldcompanyDTO = companyConvertService.convertToDTO(this.getCompanyById(id));
        if (companyDTO.getName() == null) {
            companyDTO.setName(oldcompanyDTO.getName());
        }
        this.updateCompany(companyDTO, id);
    }
}