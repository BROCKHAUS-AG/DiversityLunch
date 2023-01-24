package de.brockhausag.diversitylunchspringboot.profile.mapper;


import de.brockhausag.diversitylunchspringboot.profile.model.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements DimensionMapper<LanguageDto, LanguageEntity> {

    @Override
    public LanguageDto entityToDto(LanguageEntity entity) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(entity.getId());
        languageDto.setDescriptor(entity.getDescriptor());
        languageDto.setDefault(entity.isDefault());
        return languageDto;
    }

    @Override
    public LanguageEntity dtoToEntity(LanguageDto dto) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(dto.getId());
        languageEntity.setDescriptor(dto.getDescriptor());
        languageEntity.setDefault(dto.isDefault());
        return languageEntity;
    }
}
