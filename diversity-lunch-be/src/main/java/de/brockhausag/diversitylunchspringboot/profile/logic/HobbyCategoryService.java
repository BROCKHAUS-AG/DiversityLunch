package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.HobbyCategoryRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class HobbyCategoryService extends GenericBaseEntityService<HobbyCategoryEntity, HobbyCategoryRepository> {
    public HobbyCategoryService(HobbyCategoryRepository repository) {
        super(repository);
    }
}