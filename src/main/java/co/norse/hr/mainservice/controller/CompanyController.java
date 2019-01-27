package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.CompanyDTO;
import co.norse.hr.mainservice.entity.Company;
import co.norse.hr.mainservice.service.company.CompanyConvertService;
import co.norse.hr.mainservice.service.company.CompanyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";

    private CompanyConvertService companyConvertService;
    private CompanyQueryService companyQueryService;

    @Autowired
    public CompanyController(CompanyConvertService companyConvertService, CompanyQueryService companyQueryService) {
        this.companyConvertService = companyConvertService;
        this.companyQueryService = companyQueryService;
    }

    @PostMapping
    public Company createCompany(@RequestBody CompanyDTO companyDTO) {
        Company company = companyConvertService.convertToEntity(companyDTO);
        companyQueryService.saveCompany(company);
        return company;
    }

    @GetMapping
    public Page<Company> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable) {
        return companyQueryService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable long id) {
        return companyQueryService.getCompanyById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        companyQueryService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Company> putCompany(@PathVariable Long id,
                                              @RequestBody CompanyDTO companyDTO) {
        companyQueryService.updateCompany(companyDTO, id);
        return ResponseEntity.ok(companyConvertService.convertToEntity(companyDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Company> patchCompany(@PathVariable Long id,
                                                @RequestBody CompanyDTO companyDTO) {
        companyQueryService.patchCompany(companyDTO, id);
        return ResponseEntity.ok(companyConvertService.convertToEntity(companyDTO));
    }
}