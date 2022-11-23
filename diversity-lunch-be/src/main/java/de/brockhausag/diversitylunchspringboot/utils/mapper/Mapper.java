package de.brockhausag.diversitylunchspringboot.utils.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<DtoType, EntityType> {

    DtoType entityToDto(EntityType entity);

    EntityType dtoToEntity(DtoType dto);

    default List<DtoType> entityToDto(List<EntityType> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    default List<EntityType> dtoToEntity(List<DtoType> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
