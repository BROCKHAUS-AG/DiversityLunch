package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightedDimensionService implements DimensionService<WeightedDimension, WeightedDimensionSelectableOption> {
    private final WeightedDimensionRepository repository;
    private final WeightedDimensionSelectableOptionRepository selectableRepository;

    @Override
    public WeightedDimension getDimension(String categoryDescription) {
        return null;
    }

    @Override
    public WeightedDimension getDimension(DimensionCategory category) {
        return null;
    }

    @Override
    public List<WeightedDimension> getAllDimensions() {
        return null;
    }

    @Override
    public boolean addSelectableOption(DimensionCategory category, WeightedDimensionSelectableOption option) {
        return false;
    }

    @Override
    public boolean deleteSelectableOption(WeightedDimensionSelectableOption option) {
        return false;
    }

    @Override
    public boolean updateSelectableOption(WeightedDimensionSelectableOption option) {
        return false;
    }

    @Override
    public List<WeightedDimensionSelectableOption> getSelectableOptions(DimensionCategory category) {
        return null;
    }
}
