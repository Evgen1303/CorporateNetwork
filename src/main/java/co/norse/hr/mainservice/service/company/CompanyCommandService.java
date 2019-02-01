package co.norse.hr.mainservice.service.company;

import co.norse.hr.mainservice.dto.CompanyDTO;
import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyCommandService {
    private CompanyRepository companyRepository;
    private CompanyConvertService companyConvertService;
    private CompanyQueryService companyQueryService;

    @Autowired
    public CompanyCommandService(CompanyRepository companyRepository, CompanyConvertService companyConvertService,
                                 CompanyQueryService companyQueryService) {
        this.companyRepository = companyRepository;
        this.companyConvertService = companyConvertService;
        this.companyQueryService = companyQueryService;
    }

    public void patchCompany(CompanyDTO companyDTO, Long id) {
        CompanyDTO oldcompanyDTO = companyConvertService.convertToDTO(this.companyQueryService.getCompanyById(id));
        if (companyDTO.getName() == null) {
            companyDTO.setName(oldcompanyDTO.getName());
        }
        this.updateCompany(companyDTO, id);
    }

    public Company updateCompany(CompanyDTO companyDTO, Long id) {
        Company company = this.companyQueryService.getCompanyById(id);
        company.setName(companyDTO.getName());
        this.saveCompany(company);
        return company;
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

}
