package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BasicDimensionService implements DimensionService<
        BasicDimension,
        BasicDimensionSelectableOption> {


    private final BasicDimensionRepository repository;
    private final BasicDimensionSelectableOptionRepository selectableRepository;

    public BasicDimension getDimension(DimensionCategory category) {
        return repository.getByDimensionCategory(category);
    }

    @Override
    public List<BasicDimension> getAllDimensions() {

        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public BasicDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public BasicDimensionSelectableOption addSelectableOption(BasicDimensionSelectableOption option) {
         return selectableRepository.save(option);
    }

    @Override
    public boolean deleteSelectableOption(BasicDimensionSelectableOption option) {
        return false;
    }

    @Override
    public BasicDimensionSelectableOption updateSelectableOption(BasicDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public List<BasicDimensionSelectableOption> getSelectableOptions(DimensionCategory category) {
        return null;
    }
}
