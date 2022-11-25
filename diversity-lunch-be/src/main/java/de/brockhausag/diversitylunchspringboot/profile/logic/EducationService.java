package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class EducationService extends BaseEntityService<EducationEntity, EducationRepository> {
    public EducationService(EducationRepository repository) {
        super(repository);
    }
}
