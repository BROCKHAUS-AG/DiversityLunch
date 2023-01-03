package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.multiDimension.MultiDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.HobbyRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.springframework.stereotype.Service;

@Service
public class HobbyService extends MultiDimensionEntityService<HobbyEntity, HobbyRepository> {
    public HobbyService(HobbyRepository repository, ProfileService profileService) {
        super(repository, profileService);
    }
}
