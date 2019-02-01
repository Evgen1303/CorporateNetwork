package co.norse.hr.mainservice.service.office;

import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import co.norse.hr.mainservice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public Office getOfficeByID(Long id) {
        return officeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Office> getPages(Pageable pageable) {
        return officeRepository.findAll(pageable);
    }

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

}
