package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.HobbyCategoryRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class HobbyCategoryService extends DefaultDimensionEntityService<HobbyCategoryEntity, HobbyCategoryRepository> {
    public HobbyCategoryService(HobbyCategoryRepository repository) {
        super(repository);
    }
}
