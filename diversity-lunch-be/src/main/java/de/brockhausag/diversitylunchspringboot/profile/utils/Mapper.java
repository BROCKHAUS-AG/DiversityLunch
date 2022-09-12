package de.brockhausag.diversitylunchspringboot.profile.utils;

public interface Mapper<DtoType, EntityType> {

    DtoType entityToDto(EntityType entity);

    EntityType dtoToEntity(DtoType dto);
}
