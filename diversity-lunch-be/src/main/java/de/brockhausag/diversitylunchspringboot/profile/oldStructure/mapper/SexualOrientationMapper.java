package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SexualOrientationMapper implements DimensionMapper<SexualOrientationDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public SexualOrientationDto entityToDto(BasicDimensionSelectableOption entity) {
        SexualOrientationDto SexualOrientationDto = new SexualOrientationDto();
        SexualOrientationDto.setId(entity.getId());
        SexualOrientationDto.setDescriptor(entity.getValue());
        return SexualOrientationDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(SexualOrientationDto dto) {
        return repository.getByDimensionCategory_Description(dto.getDescriptor());
    }
}
