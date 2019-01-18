package co.norse.hr.mainservice.service.office;

import co.norse.hr.mainservice.entity.Office;
import co.norse.hr.mainservice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OfficeQueryService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeQueryService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Office findOneOrThrowException(Long id) {
        return officeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
