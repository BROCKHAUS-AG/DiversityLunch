package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.profile.modelTest.CreateProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.ProfileEntity;

public interface ProfileMapper {

    ProfileEntity mapCreateDtoToEntity(CreateProfileDto profile);

    ProfileEntity mapDtoToEntity(ProfileDto profile);

    ProfileDto mapEntityToDto(ProfileEntity profile);
}
