package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BasicDimensionService implements DimensionService<BasicDimension, BasicDimensionSelectableOption> {
    private final BasicDimensionRepository repository;
    private final BasicDimensionSelectableOptionRepository selectableRepository;

    @Override
    public BasicDimension getDimension(String categoryDescription) {
        return null;
    }

    @Override
    public BasicDimension getDimension(DimensionCategory category) {
        return null;
    }

    @Override
    public List<BasicDimension> getAllDimensions() {
        return null;
    }

    @Override
    public boolean addSelectableOption(DimensionCategory category, BasicDimensionSelectableOption option) {
        return false;
    }

    @Override
    public boolean deleteSelectableOption(BasicDimensionSelectableOption option) {
        return false;
    }

    @Override
    public boolean updateSelectableOption(BasicDimensionSelectableOption option) {
        return false;
    }

    @Override
    public List<BasicDimensionSelectableOption> getSelectableOptions(DimensionCategory category) {
        return null;
    }
}
