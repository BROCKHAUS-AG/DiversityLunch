package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.weightedDimension.WeightedEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.WorkExperienceRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkExperienceService extends WeightedEntityService<WorkExperienceEntity, WorkExperienceRepository> {
    public WorkExperienceService(WorkExperienceRepository repository) {
        super(repository);
    }
}
