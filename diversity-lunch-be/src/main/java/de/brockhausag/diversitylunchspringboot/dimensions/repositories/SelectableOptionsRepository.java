package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SelectableOptionsRepository<Selectable extends SelectableOptions> extends CrudRepository<Selectable, Long> {

}
