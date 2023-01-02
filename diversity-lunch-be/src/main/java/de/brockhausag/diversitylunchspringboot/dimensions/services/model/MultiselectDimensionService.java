package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MultiselectDimensionService implements DimensionService<MultiselectDimension, MultiselectDimensionSelectableOption> {
    private final MultiselectDimensionRepository repository;
    private final MultiselectDimensionSelectableOptionRepository selectableRepository;

    @Override
    public MultiselectDimension getDimension(String categoryDescription) {
        return null;
    }

    @Override
    public MultiselectDimension getDimension(DimensionCategory category) {
        return null;
    }

    @Override
    public List<MultiselectDimension> getAllDimensions() {
        return null;
    }

    @Override
    public boolean addSelectableOption(DimensionCategory category, MultiselectDimensionSelectableOption option) {
        return false;
    }

    @Override
    public boolean deleteSelectableOption(MultiselectDimensionSelectableOption option) {
        return false;
    }

    @Override
    public boolean updateSelectableOption(MultiselectDimensionSelectableOption option) {
        return false;
    }

    @Override
    public List<MultiselectDimensionSelectableOption> getSelectableOptions(DimensionCategory category) {
        return null;
    }
}
