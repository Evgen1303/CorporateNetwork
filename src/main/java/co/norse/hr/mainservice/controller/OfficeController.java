package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.dto.OfficeDTO;
import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.service.office.OfficeCommandService;
import co.norse.hr.mainservice.service.office.OfficeConvertService;
import co.norse.hr.mainservice.service.office.OfficeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("offices")
public class OfficeController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";

    private OfficeConvertService officeConvertService;
    private OfficeQueryService officeQueryService;
    private OfficeCommandService officeCommandService;

    @Autowired
    public OfficeController(OfficeConvertService officeConvertService, OfficeQueryService officeQueryService,
                            OfficeCommandService officeCommandService) {
        this.officeConvertService = officeConvertService;
        this.officeQueryService = officeQueryService;
        this.officeCommandService = officeCommandService;
    }

    @PostMapping
    public Office createOffice(@RequestBody OfficeDTO officeDTO) {
        Office office = officeConvertService.convertToEntity(officeDTO);
        officeCommandService.saveOffice(office);
        return office;
    }

    @GetMapping
    public Page<Office> getOffices(@PageableDefault(size = DEFAULT_PAGE_SIZE)
                                   @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                                           Pageable pageable) {
        return officeQueryService.getPages(pageable);
    }

    @GetMapping("/{id}")
    public Office getOffice(@PathVariable Long id) {
        return officeQueryService.getOfficeByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOffice(@PathVariable Long id) {
        officeCommandService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> putOffice(@PathVariable Long id, @RequestBody OfficeDTO officeDTO) {
        officeCommandService.updateOffice(officeDTO, id);
        return ResponseEntity.ok(officeConvertService.convertToEntity(officeDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Office> patchOffice(@PathVariable Long id, @RequestBody OfficeDTO officeDTO) {
        officeCommandService.patchOffice(officeDTO, id);
        return ResponseEntity.ok(officeConvertService.convertToEntity(officeDTO));
    }
}
