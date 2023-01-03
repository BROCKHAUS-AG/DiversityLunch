package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.LanguageRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends DefaultDimensionEntityService<LanguageEntity, LanguageRepository> {
    public LanguageService(LanguageRepository repository, ProfileService profileService) {
        super(repository, profileService);
    }
}
