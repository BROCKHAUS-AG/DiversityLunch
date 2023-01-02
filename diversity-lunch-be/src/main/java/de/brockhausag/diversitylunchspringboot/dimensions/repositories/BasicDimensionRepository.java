package de.brockhausag.diversitylunchspringboot.dimensions.repositories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import org.springframework.data.repository.CrudRepository;

public interface BasicDimensionRepository extends CrudRepository<BasicDimension, Long> {
}
