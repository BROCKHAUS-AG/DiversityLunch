package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.SexualOrientationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import org.springframework.stereotype.Service;

@Service
public class SexualOrientationService extends DefaultDimensionEntityService<SexualOrientationEntity, SexualOrientationRepository> {
    public SexualOrientationService(SexualOrientationRepository repository) {
        super(repository);
    }
}
