package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.DietRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericServiceForBaseEntity;
import org.springframework.stereotype.Service;

@Service
public class DietService extends GenericServiceForBaseEntity<DietEntity, DietRepository> {
    public DietService(DietRepository repository)
    {
        super(repository);
    }
}
