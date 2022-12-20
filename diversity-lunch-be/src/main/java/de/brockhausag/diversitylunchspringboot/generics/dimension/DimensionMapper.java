package de.brockhausag.diversitylunchspringboot.generics.dimension;

import java.util.List;
import java.util.stream.Collectors;

public interface DimensionMapper<DtoType, EntityType> {

    DtoType entityToDto(EntityType entity);

    EntityType dtoToEntity(DtoType dto);

    default List<DtoType> entityToDto(List<EntityType> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    default List<EntityType> dtoToEntity(List<DtoType> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
