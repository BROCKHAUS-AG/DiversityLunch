package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DimensionRepository<DimensionType extends Dimension<Selectable>, Selectable extends SelectableOptions> extends CrudRepository<DimensionType, Long> {
    DimensionType findByDimensionCategory_Description(String categoryDescription);
}
