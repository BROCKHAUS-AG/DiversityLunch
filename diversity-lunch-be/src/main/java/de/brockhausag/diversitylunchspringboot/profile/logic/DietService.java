package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.DietRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import org.springframework.stereotype.Service;

@Service
public class DietService extends DefaultDimensionEntityService<DietEntity, DietRepository> {
    public DietService(DietRepository repository) {
        super(repository);
    }
}
