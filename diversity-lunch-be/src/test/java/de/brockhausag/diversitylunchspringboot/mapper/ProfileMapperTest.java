package de.brockhausag.diversitylunchspringboot.mapper;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

    @Mock
    private EducationMapper educationMapper;
    @Mock
    private DietMapper dietMapper;
    @Mock
    private GenderMapper genderMapper;
    @Mock
    private LanguageMapper languageMapper;
    @Mock
    private CountryMapper countryMapper;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private ReligionMapper religionMapper;

    @InjectMocks
    private ProfileMapper profileMapper;
    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();

    @BeforeEach
    void setup(){
        this.educationMapper = new EducationMapper();
        this.dietMapper = new DietMapper();
        this.genderMapper = new GenderMapper();
        this.languageMapper = new LanguageMapper();
        this.countryMapper = new CountryMapper();
        this.projectMapper = new ProjectMapper();
        this.religionMapper = new ReligionMapper();
        this.profileMapper = new ProfileMapper(
                educationMapper,
                dietMapper,
                genderMapper,
                languageMapper,
                countryMapper,
                projectMapper,
                religionMapper
        );
    }

    @Test
    void testDtoToEntity_withOneEntity_returnsOneDto() {
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
