package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.SocialBackgroundDiscriminationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundDiscriminationEntity;
import org.springframework.stereotype.Service;

@Service
public class SocialBackgroundDiscriminationService extends DefaultDimensionEntityService<SocialBackgroundDiscriminationEntity, SocialBackgroundDiscriminationRepository> {
    public SocialBackgroundDiscriminationService(SocialBackgroundDiscriminationRepository repository) {
        super(repository);
    }
}



