package de.brockhausag.diversitylunchspringboot.profile.mapper;


import de.brockhausag.diversitylunchspringboot.profile.model.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LanguageMapper implements Mapper<LanguageDto, LanguageEntity> {

    @Override
    public LanguageDto entityToDto(LanguageEntity entity) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(entity.getId());
        languageDto.setDescriptor(entity.getDescriptor());
        return languageDto;
    }

    @Override
    public LanguageEntity dtoToEntity(LanguageDto dto) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(dto.getId());
        languageEntity.setDescriptor(dto.getDescriptor());
        return languageEntity;
    }

    @Override
    public List<LanguageDto> entityToDto(List<LanguageEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<LanguageEntity> dtoToEntity(List<LanguageDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
