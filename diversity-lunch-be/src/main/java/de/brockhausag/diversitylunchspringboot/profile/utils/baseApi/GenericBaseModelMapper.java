package de.brockhausag.diversitylunchspringboot.profile.utils.baseApi;

import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GenericBaseModelMapper<DtoType extends BaseDto, EntityType extends BaseEntity> implements Mapper<DtoType, EntityType> {

    private final Supplier<DtoType> dtoSupplier;
    private final Supplier<EntityType> entitySupplier;

    public GenericBaseModelMapper(Supplier<DtoType> dtoSupplier, Supplier<EntityType> entitySupplier) {
        this.dtoSupplier = dtoSupplier;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public DtoType entityToDto(EntityType entity) {
        DtoType dto = dtoSupplier.get();
        dto.setId(entity.getId());
        dto.setDescriptor(entity.getDescriptor());
        return dto;
    }

    @Override
    public EntityType dtoToEntity(DtoType dto) {
        EntityType entity = entitySupplier.get();
        entity.setId(dto.getId());
        entity.setDescriptor(dto.getDescriptor());
        return entity;
    }

    @Override
    public List<DtoType> entityToDto(List<EntityType> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<EntityType> dtoToEntity(List<DtoType> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
