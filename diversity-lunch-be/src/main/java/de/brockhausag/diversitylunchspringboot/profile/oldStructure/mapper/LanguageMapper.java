package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageMapper implements DimensionMapper<LanguageDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public LanguageDto entityToDto(BasicDimensionSelectableOption entity) {
        LanguageDto LanguageDto = new LanguageDto();
        LanguageDto.setId(entity.getId());
        LanguageDto.setDescriptor(entity.getValue());
        return LanguageDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(LanguageDto dto) {
        return repository.getByDimensionCategory_Description(dto.getDescriptor());
    }
}
