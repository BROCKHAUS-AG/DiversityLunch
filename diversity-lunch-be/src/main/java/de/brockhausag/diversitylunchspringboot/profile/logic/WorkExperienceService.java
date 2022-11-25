package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.WorkExperienceRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class WorkExperienceService extends BaseEntityService<WorkExperienceEntity, WorkExperienceRepository> {
    public WorkExperienceService(WorkExperienceRepository repository) {
        super(repository);
    }
}
