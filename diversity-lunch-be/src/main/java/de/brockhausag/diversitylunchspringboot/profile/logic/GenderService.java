package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.GenderRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class GenderService extends GenericBaseEntityService<GenderEntity, GenderRepository> {
    public GenderService(GenderRepository repository) {
        super(repository);
    }
}
