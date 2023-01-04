package de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;

import java.util.List;
import java.util.stream.Collectors;

public interface DimensionMapper<DtoType, DimensionType extends SelectableOptions> {

    DtoType entityToDto(DimensionType entity);

    DimensionType dtoToEntity(DtoType dto);

    default List<DtoType> entityToDto(List<DimensionType> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    default List<DimensionType> dtoToEntity(List<DtoType> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
