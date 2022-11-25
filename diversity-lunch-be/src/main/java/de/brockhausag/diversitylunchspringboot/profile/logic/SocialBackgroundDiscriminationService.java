package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.SocialBackgroundDiscriminationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundDiscriminationEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class SocialBackgroundDiscriminationService extends BaseEntityService<SocialBackgroundDiscriminationEntity, SocialBackgroundDiscriminationRepository> {
    public SocialBackgroundDiscriminationService(SocialBackgroundDiscriminationRepository repository) {
        super(repository);
    }
}



