package co.norse.hr.mainservice.service.office;

import co.norse.hr.mainservice.dto.OfficeDTO;
import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeCommandService {
    private OfficeRepository officeRepository;
    private OfficeConvertService officeConvertService;
    private OfficeQueryService officeQueryService;

    @Autowired
    public OfficeCommandService(OfficeRepository officeRepository, OfficeConvertService officeConvertService,
                                OfficeQueryService officeQueryService) {
        this.officeRepository = officeRepository;
        this.officeConvertService = officeConvertService;
        this.officeQueryService = officeQueryService;
    }

    public void patchOffice(OfficeDTO officeDTO, Long id) {
        OfficeDTO oldOfficeDTO = officeConvertService.convertToDTO(this.officeQueryService.getOfficeByID(id));
        if (officeDTO.getName() == null) {
            officeDTO.setName(oldOfficeDTO.getName());
        }
        if (officeDTO.getCity() == null) {
            officeDTO.setCity(oldOfficeDTO.getCity());
        }
        this.updateOffice(officeDTO, id);
    }

    public Office updateOffice(OfficeDTO officeDTO, Long id) {
        Office office = this.officeQueryService.getOfficeByID(id);
        office.setName(officeDTO.getName());
        office.setCity(officeDTO.getCity());
        this.saveOffice(office);
        return office;
    }

    public void saveOffice(Office office) {
        officeRepository.save(office);
    }

    public void deleteOffice(Long id) {
        officeRepository.deleteById(id);
    }

    public void deleteOffice(Office office) {
        officeRepository.delete(office);
    }

}
