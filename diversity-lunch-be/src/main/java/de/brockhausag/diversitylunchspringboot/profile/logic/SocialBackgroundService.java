package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.SocialBackgroundRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundEntity;
import org.springframework.stereotype.Service;

@Service
public class SocialBackgroundService extends DefaultDimensionEntityService<SocialBackgroundEntity, SocialBackgroundRepository> {
    public SocialBackgroundService(SocialBackgroundRepository repository, ProfileService profileService) {
        super(repository, profileService);
    }
}

