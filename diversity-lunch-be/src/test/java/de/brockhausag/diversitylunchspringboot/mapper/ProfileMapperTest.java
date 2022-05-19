package de.brockhausag.diversitylunchspringboot.mapper;

import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ModelMapperProfileMapper;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProfileMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.CreateProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileMapperTest {

    private final ProfileMapper profileMapper = new ModelMapperProfileMapper(new ModelMapper());
    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();

    @Test
    void testMapCreateDtoToEntity() {
        ProfileEntity expected = this.factory.createEntity();
        CreateProfileDto createDto = this.factory.createDto();

        ProfileEntity result = this.profileMapper.mapCreateDtoToEntity(createDto);

        assertEquals(expected, result);
    }

    @Test
    void testMapDtoToEntity() {
        ProfileEntity expected = this.factory.entity();
        ProfileDto dto = this.factory.dto();

        ProfileEntity result = this.profileMapper.mapDtoToEntity(dto);

        assertEquals(expected, result);
    }

    @Test
    void testMapEntityToDto() {
        ProfileDto expected = this.factory.dto();
        ProfileEntity entity = this.factory.entity();

        ProfileDto result = this.profileMapper.mapEntityToDto(entity);

        assertEquals(expected, result);
    }
}
