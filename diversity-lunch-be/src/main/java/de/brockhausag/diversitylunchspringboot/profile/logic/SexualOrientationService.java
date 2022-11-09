package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.SexualityRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class SexualOrientationService extends GenericBaseEntityService<SexualOrientationEntity, SexualityRepository> {
    public SexualOrientationService(SexualityRepository repository){super(repository);}
}
