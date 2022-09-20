package de.brockhausag.diversitylunchspringboot.profile.utils.genericOverload;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BaseModelMapper<DtoType extends GenericMapperBaseDto<DtoType>, EntityType extends GenericMapperBaseEntity<EntityType>> {

    private final DtoType emptyDto;
    private final EntityType emptyEntity;

    public BaseModelMapper(DtoType emptyDto, EntityType emptyEntity) {
        this.emptyDto = emptyDto;
        this.emptyEntity = emptyEntity;
    }

    public DtoType entityToDto(EntityType entity) {
        DtoType returnDto = emptyDto.clone();
        returnDto.setId(entity.getId());
        returnDto.setDescriptor(entity.getDescriptor());
        return returnDto;
    }
    
    public EntityType dtoToEntity(DtoType dto) {
        EntityType returnEntity = emptyEntity.clone();
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
