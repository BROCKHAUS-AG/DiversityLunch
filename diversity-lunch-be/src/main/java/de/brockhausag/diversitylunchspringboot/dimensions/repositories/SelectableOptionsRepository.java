package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SelectableOptionsRepository<Selectable extends SelectableOptions> extends CrudRepository<Selectable, Long> {
    List<Selectable> getByDimensionCategory(DimensionCategory category);
    Optional<Selectable> findByDimensionCategoryAndValue(DimensionCategory category, String description);
}


