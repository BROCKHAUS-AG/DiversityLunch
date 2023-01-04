package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HobbyMapper implements DimensionMapper<HobbyDto, MultiselectDimensionSelectableOption> {

    private final MultiselectDimensionSelectableOptionRepository repository;

    @Override
    public HobbyDto entityToDto(MultiselectDimensionSelectableOption entity) {
        HobbyDto HobbyDto = new HobbyDto();
        HobbyDto.setId(entity.getDimensionCategory().getId());
        HobbyDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return HobbyDto;
    }

    @Override
    public MultiselectDimensionSelectableOption dtoToEntity(HobbyDto dto) {
        return repository.findByDimensionCategoryByDescription(dto.getDescriptor());
    }
}
