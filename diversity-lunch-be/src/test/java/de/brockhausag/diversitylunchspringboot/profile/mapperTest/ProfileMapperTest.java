package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

    @Mock
    private CountryMapper countryMapper;
    @Mock
    private DietMapper dietMapper;
    @Mock
    private EducationMapper educationMapper;
    @Mock
    private GenderMapper genderMapper;
    @Mock
    private LanguageMapper languageMapper;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private ReligionMapper religionMapper;

    @InjectMocks
    private ProfileMapper profileMapper;
    private ProfileTestdataFactory factory;

    @BeforeEach
    void setup(){
        this.factory = new ProfileTestdataFactory();
        this.educationMapper = new EducationMapper();
        this.dietMapper = new DietMapper();
        this.genderMapper = new GenderMapper();
        this.languageMapper = new LanguageMapper();
        this.countryMapper = new CountryMapper();
        this.projectMapper = new ProjectMapper();
        this.religionMapper = new ReligionMapper();
        this.profileMapper = new ProfileMapper();
    }

    @Test
    void testDtoToEntity_withOneEntity_returnsOneDto() {
        //Arrange
        ProfileDto inputDto = factory.buildDto(1);
        ProfileEntity expectedEntity = factory.buildEntity(1);

        when(educationMapper.dtoToEntity(inputDto.getEducation())).thenReturn(expectedEntity.getEducation());
        when(dietMapper.dtoToEntity(inputDto.getDiet())).thenReturn(expectedEntity.getDiet());
        when(genderMapper.dtoToEntity(inputDto.getGender())).thenReturn(expectedEntity.getGender());
        when(languageMapper.dtoToEntity(inputDto.getMotherTongue())).thenReturn(expectedEntity.getMotherTongue());
        when(countryMapper.dtoToEntity(inputDto.getOriginCountry())).thenReturn(expectedEntity.getOriginCountry());
        when(projectMapper.dtoToEntity(inputDto.getProjects())).thenReturn(expectedEntity.getProjects());
        when(religionMapper.dtoToEntity(inputDto.getReligion())).thenReturn(expectedEntity.getReligion());
        //Act
        ProfileEntity actualEntity = profileMapper.dtoToEntity(inputDto);

        //Assert
        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testMapEntityToDto() {

    }
}
