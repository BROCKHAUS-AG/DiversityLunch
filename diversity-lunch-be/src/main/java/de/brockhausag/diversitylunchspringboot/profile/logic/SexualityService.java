package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.SexualityRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualityEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;

public class SexualityService extends GenericBaseEntityService<SexualityEntity, SexualityRepository> {
    public SexualityService(SexualityRepository repository){super(repository);}
}
