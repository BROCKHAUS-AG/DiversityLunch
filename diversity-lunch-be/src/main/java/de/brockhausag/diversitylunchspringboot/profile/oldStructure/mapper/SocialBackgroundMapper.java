package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialBackgroundMapper implements DimensionMapper<SocialBackgroundDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public SocialBackgroundDto entityToDto(BasicDimensionSelectableOption entity) {
        SocialBackgroundDto SocialBackgroundDto = new SocialBackgroundDto();
        SocialBackgroundDto.setId(entity.getDimensionCategory().getId());
        SocialBackgroundDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return SocialBackgroundDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(SocialBackgroundDto dto) {
        return repository.findByDimensionCategoryByDescription(dto.getDescriptor());
    }
}
