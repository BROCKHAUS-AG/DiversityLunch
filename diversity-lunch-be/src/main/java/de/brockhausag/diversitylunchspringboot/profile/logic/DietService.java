package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.DietRepository;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class DietService extends GenericBaseEntityService<DietEntity, DietRepository> {
    public DietService(DietRepository repository) {
        super(repository);
    }
}
