package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.GenderRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;
import org.springframework.stereotype.Service;

@Service
public class GenderService extends DefaultDimensionEntityService<GenderEntity, GenderRepository> {
    public GenderService(GenderRepository repository) {
        super(repository);
    }
}
