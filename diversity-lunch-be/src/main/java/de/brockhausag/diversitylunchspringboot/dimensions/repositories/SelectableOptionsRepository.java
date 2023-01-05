package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface SelectableOptionsRepository<Selectable extends SelectableOptions> extends CrudRepository<Selectable, Long> {
    List<Selectable> getByDimensionCategory_Description(String categoryDescription);
    List<Selectable> getByDimensionCategory_Id(Long categoryId);
    Selectable getByDimensionCategory_IdAndValue(Long categoryId, String categoryDescription);
    Selectable getById(Long id);
}


