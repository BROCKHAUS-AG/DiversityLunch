package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.ReligionRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;
import org.springframework.stereotype.Service;

@Service
public class ReligionService extends DefaultDimensionEntityService<ReligionEntity, ReligionRepository> {
    public ReligionService(ReligionRepository repository, ProfileService profileService) {
        super(repository, profileService);
    }
}