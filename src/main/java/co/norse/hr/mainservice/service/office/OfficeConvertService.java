package co.norse.hr.mainservice.service.office;

import co.norse.hr.mainservice.dto.OfficeDTO;
import co.norse.hr.mainservice.entity.Office;
import org.springframework.stereotype.Service;

@Service
public class OfficeConvertService {

    public OfficeDTO convertToDTO(Office office) {
        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setName(office.getName());
        officeDTO.setCity(office.getCity());
        return officeDTO;
    }

    public Office convertToEntity(OfficeDTO officeDTO) {
        Office office = new Office();
        office.setCity(officeDTO.getCity());
        office.setName(officeDTO.getName());
        return office;
    }
}
