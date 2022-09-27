package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenderMapper implements Mapper<GenderDto, GenderEntity> {
   
    @Override
    public GenderDto entityToDto(GenderEntity entity) {
        GenderDto genderDto = new GenderDto();
        genderDto.setId(entity.getId());
        genderDto.setDescriptor(entity.getDescriptor());
        return genderDto;
    }

    @Override
    public GenderEntity dtoToEntity(GenderDto dto) {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setId(dto.getId());
        genderEntity.setDescriptor(dto.getDescriptor());
        return genderEntity;
    }

    @Override
    public List<GenderDto> entityToDto(List<GenderEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<GenderEntity> dtoToEntity(List<GenderDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
