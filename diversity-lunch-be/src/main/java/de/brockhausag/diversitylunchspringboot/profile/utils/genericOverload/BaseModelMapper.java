package de.brockhausag.diversitylunchspringboot.profile.utils.genericOverload;

import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseDto;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseEntity;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BaseModelMapper<DtoType extends BaseDto, EntityType extends BaseEntity> {

    private final Supplier<EntityType> entityInstanceSupplier;
    private final Supplier<DtoType> dtoInstanceSupplier;
    public BaseModelMapper(Supplier<EntityType> entityInstanceSupplier, Supplier<DtoType> dtoInstanceSupplier) {
        this.entityInstanceSupplier = entityInstanceSupplier;
        this.dtoInstanceSupplier = dtoInstanceSupplier;
    }

    public DtoType entityToDto(EntityType entity) {
        DtoType returnDto = dtoInstanceSupplier.get();
        returnDto.setId(entity.getId());
        returnDto.setDescriptor(entity.getDescriptor());
        return returnDto;
    }
    
    public EntityType dtoToEntity(DtoType dto) {
        EntityType returnEntity = entityInstanceSupplier.get();
        returnEntity.setId(dto.getId());
        returnEntity.setDescriptor(dto.getDescriptor());
        return returnEntity;
    }
    
    public List<DtoType> entityToDto(List<EntityType> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
    
    public List<EntityType> dtoToEntity(List<DtoType> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
