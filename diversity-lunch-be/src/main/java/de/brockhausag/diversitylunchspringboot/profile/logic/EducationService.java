package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericServiceForBaseEntity;
import org.springframework.stereotype.Service;

@Service
public class EducationService extends GenericServiceForBaseEntity<EducationEntity, EducationRepository> {
    public EducationService(EducationRepository repository) {
        super(repository);
    }
}