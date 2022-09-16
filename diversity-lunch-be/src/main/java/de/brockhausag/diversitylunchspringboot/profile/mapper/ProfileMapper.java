package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.CreateProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

public interface ProfileMapper {

    ProfileEntity mapCreateDtoToEntity(CreateProfileDto profile);

    ProfileEntity mapDtoToEntity(ProfileDto profile);

    ProfileDto mapEntityToDto(ProfileEntity profile);
}
