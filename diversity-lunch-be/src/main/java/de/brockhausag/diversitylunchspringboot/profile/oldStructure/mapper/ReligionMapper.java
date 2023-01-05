package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReligionMapper implements DimensionMapper<ReligionDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public ReligionDto entityToDto(BasicDimensionSelectableOption entity) {
        ReligionDto ReligionDto = new ReligionDto();
        ReligionDto.setId(entity.getId());
        ReligionDto.setDescriptor(entity.getValue());
        return ReligionDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(ReligionDto dto) {
        return repository.getById(dto.getId());
    }
}
