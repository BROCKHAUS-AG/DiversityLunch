package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class EducationService extends GenericBaseEntityService<EducationEntity, EducationRepository> {
    public EducationService(EducationRepository repository) {
        super(repository);
    }
}