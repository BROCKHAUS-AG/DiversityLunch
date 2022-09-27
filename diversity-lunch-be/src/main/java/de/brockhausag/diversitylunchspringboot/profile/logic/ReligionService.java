package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.ReligionRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class ReligionService extends GenericBaseEntityService<ReligionEntity, ReligionRepository> {
    public ReligionService(ReligionRepository repository) {
        super(repository);
    }
}