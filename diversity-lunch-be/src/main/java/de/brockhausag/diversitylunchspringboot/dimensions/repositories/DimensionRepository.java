package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface DimensionRepository<DimensionType extends Dimension<Selectable>, Selectable extends SelectableOptions> extends CrudRepository<DimensionType, Long> {
    Optional<DimensionType> findByDimensionCategory_Description(String categoryDescription);
    Optional<DimensionType> findByDimensionCategory(DimensionCategory dimensionCategory);
}