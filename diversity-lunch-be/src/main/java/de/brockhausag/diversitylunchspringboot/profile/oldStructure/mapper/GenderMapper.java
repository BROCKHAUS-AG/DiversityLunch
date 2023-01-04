package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenderMapper implements DimensionMapper<GenderDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public GenderDto entityToDto(BasicDimensionSelectableOption entity) {
        GenderDto GenderDto = new GenderDto();
        GenderDto.setId(entity.getDimensionCategory().getId());
        GenderDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return GenderDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(GenderDto dto) {
        return repository.findByDimensionCategoryByDescription(dto.getDescriptor());
    }
}
