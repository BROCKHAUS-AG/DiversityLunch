package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DietMapper implements DimensionMapper<DietDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public DietDto entityToDto(BasicDimensionSelectableOption entity) {
        DietDto DietDto = new DietDto();
        DietDto.setId(entity.getDimensionCategory().getId());
        DietDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return DietDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(DietDto dto) {
        return repository.getByDimensionCategory_Description(dto.getDescriptor());
    }
}
