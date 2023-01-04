package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.SocialBackgroundDiscriminationDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialBackgroundDiscriminationMapper implements DimensionMapper<SocialBackgroundDiscriminationDto, BasicDimensionSelectableOption> {

    private final BasicDimensionSelectableOptionRepository repository;

    @Override
    public SocialBackgroundDiscriminationDto entityToDto(BasicDimensionSelectableOption entity) {
        SocialBackgroundDiscriminationDto SocialBackgroundDiscriminationDto = new SocialBackgroundDiscriminationDto();
        SocialBackgroundDiscriminationDto.setId(entity.getDimensionCategory().getId());
        SocialBackgroundDiscriminationDto.setDescriptor(entity.getDimensionCategory().getDescription());
        return SocialBackgroundDiscriminationDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(SocialBackgroundDiscriminationDto dto) {
        return repository.findByDimensionCategoryByDescription(dto.getDescriptor());
    }
}
