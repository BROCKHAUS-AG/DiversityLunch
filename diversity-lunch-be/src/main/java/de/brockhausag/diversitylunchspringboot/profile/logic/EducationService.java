package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import org.springframework.stereotype.Service;

@Service
public class EducationService extends DefaultDimensionEntityService<EducationEntity, EducationRepository> {
    public EducationService(EducationRepository repository) {
        super(repository);
    }
}
