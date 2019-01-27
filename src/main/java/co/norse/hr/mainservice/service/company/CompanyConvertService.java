package co.norse.hr.mainservice.service.company;

import co.norse.hr.mainservice.dto.CompanyDTO;
import co.norse.hr.mainservice.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyConvertService {
    public CompanyDTO convertToDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        return companyDTO;
    }

    public Company convertToEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        return company;
    }
}