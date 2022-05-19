package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.CreateProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelMapperProfileMapper implements ProfileMapper {

    private final ModelMapper modelMapper;

    @Override
    public ProfileEntity mapCreateDtoToEntity(final CreateProfileDto createProfileDto) {
        return this.modelMapper.map(createProfileDto, ProfileEntity.class);
    }

    @Override
    public ProfileEntity mapDtoToEntity(final ProfileDto profileDto) {
        return modelMapper.map(profileDto, ProfileEntity.class);
    }

    @Override
    public ProfileDto mapEntityToDto(ProfileEntity entity) {
        return this.modelMapper.map(entity, ProfileDto.class);
    }
}
