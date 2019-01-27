package co.norse.hr.mainservice.service.office;

import co.norse.hr.mainservice.dto.OfficeDTO;
import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OfficeQueryService {

    private OfficeRepository officeRepository;
    private OfficeConvertService officeConvertService;

    @Autowired
    public OfficeQueryService(OfficeRepository officeRepository, OfficeConvertService officeConvertService) {
        this.officeRepository = officeRepository;
        this.officeConvertService = officeConvertService;
    }

    public Office findOneOrThrowException(Long id) {
        return officeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Office getOfficeByID(Long id){
        Optional<Office> result= officeRepository.findById(id);
        return result.orElseThrow(ResourceNotFoundException::new);
     }

    public void saveOffice(Office office){
        officeRepository.save(office);
    }
    public void deleteOffice(Long id){
        officeRepository.deleteById(id);
    }
    public void deleteAllOffices(){
        officeRepository.deleteAll();
    }
    public void deleteOffice(Office office){
        officeRepository.delete(office);
    }
    public Office updateOffice(OfficeDTO officeDTO, Long id){
        Office office = officeConvertService.convertToEntity(officeDTO);
        office.setId(id);
        this.saveOffice(office);
        return office;
    }
    public Page<Office> getPage(Pageable pageable){
        return officeRepository.findAll(pageable);
    }
    public Iterable<Office> getAllOffices(){
        Iterable<Office> offices= officeRepository.findAll();
        if (!offices.iterator().hasNext()) {
            throw new ResourceNotFoundException();
        }
        return offices;
    }

    public void patchOffice(OfficeDTO officeDTO, Long id){
        OfficeDTO oldOfficeDTO = officeConvertService.convertToDTO(this.getOfficeByID(id));
        if (officeDTO.getName()==null){
            officeDTO.setName(oldOfficeDTO.getName());
        }
        if(officeDTO.getCity()== 0){
            officeDTO.setCity(oldOfficeDTO.getCity());
        }
        this.updateOffice(officeDTO,id);
    }
}
