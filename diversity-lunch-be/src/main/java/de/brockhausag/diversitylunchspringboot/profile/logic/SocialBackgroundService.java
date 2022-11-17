package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.SocialBackgroundRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;

public class SocialBackgroundService extends GenericBaseEntityService<SocialBackgroundEntity, SocialBackgroundRepository> {
    public SocialBackgroundService(SocialBackgroundRepository repository) {
        super(repository);
    }
}

