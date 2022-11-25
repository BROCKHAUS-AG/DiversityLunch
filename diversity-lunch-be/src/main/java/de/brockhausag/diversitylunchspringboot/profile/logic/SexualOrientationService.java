package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.SexualOrientationRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class SexualOrientationService extends BaseEntityService<SexualOrientationEntity, SexualOrientationRepository> {
    public SexualOrientationService(SexualOrientationRepository repository){super(repository);}
}
