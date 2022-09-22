package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.profile.data.HobbyRepository;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class HobbyService extends GenericBaseEntityService<HobbyEntity, HobbyRepository> {
    public HobbyService(HobbyRepository repository) {
        super(repository);
    }
}
