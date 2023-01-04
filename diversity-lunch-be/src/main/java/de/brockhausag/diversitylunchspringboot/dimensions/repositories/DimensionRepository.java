package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DimensionRepository<DimensionType extends Dimension<Selectable>, Selectable extends SelectableOptions> extends CrudRepository<DimensionType, Long> {
    DimensionType getByDimensionCategory_Description(String categoryDescription);

    DimensionType getByDimensionCategory(DimensionCategory dimensionCategory);
}

