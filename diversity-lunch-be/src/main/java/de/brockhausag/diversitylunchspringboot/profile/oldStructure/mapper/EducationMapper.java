package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationMapper implements DimensionMapper<EducationDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public EducationDto entityToDto(BasicDimensionSelectableOption entity) {
        EducationDto EducationDto = new EducationDto();
        EducationDto.setId(entity.getDimensionCategory().getId());
        EducationDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return EducationDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(EducationDto dto) {
        return repository.getByDimensionCategory_Description(dto.getDescriptor());
    }
}
