package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.util.StreamUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProfileMapperTest {

    @Mock
    private BasicDimensionService basicDimensionService;

    @Mock
    private MultiselectDimensionService multiselectDimService;

    @Mock
    private WeightedDimensionService weightedDimService;

    @Spy
    private CountryMapper countryMapper = new CountryMapper(basicDimensionService);
    @Spy
    private DietMapper dietMapper = new DietMapper(basicDimensionService);
    @Spy
    private EducationMapper educationMapper = new EducationMapper(basicDimensionService);
    @Spy
    private GenderMapper genderMapper = new GenderMapper(basicDimensionService);
    @Spy
    private HobbyMapper hobbyMapper = new HobbyMapper(multiselectDimService);
    @Spy
    private LanguageMapper languageMapper = new LanguageMapper(basicDimensionService);
    @Spy
    private ProjectMapper projectMapper = new ProjectMapper(basicDimensionService);
    @Spy
    private ReligionMapper religionMapper = new ReligionMapper(basicDimensionService);
    @Spy
    private SexualOrientationMapper sexualOrientationMapper = new SexualOrientationMapper(basicDimensionService);
    @Spy
    private WorkExperienceMapper workExperienceMapper = new WorkExperienceMapper(weightedDimService);
    @Spy
    private SocialBackgroundMapper socialBackgroundMapper = new SocialBackgroundMapper(basicDimensionService);
    @Spy
    private SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper = new SocialBackgroundDiscriminationMapper(basicDimensionService);

    @InjectMocks
    private ProfileMapper profileMapper;
    private ProfileTestdataFactory factory;

    private ProfileDto profileDto;
    private ProfileEntity profileEntity;
    private BasicDimensionTestDataFactory basicDimensionTestDataFactory;
    private BasicSelectableOptionTestDataFactory basicSelectableOptionTestDataFactory;
    private WeightedDimensionTestDataFactory weightedDimensionTestDataFactory;
    private WeightedSelectableOptionTestDataFactory weightedSelectableOptionTestDataFactory;
    private MultiselectDimensionTestDataFactory multiselectDimensionTestDataFactory;
    private MultiselectSelectableOptionTestDataFactory multiselectSelectableOptionTestDataFactory;

    @BeforeEach
    void setup() {
        this.factory = new ProfileTestdataFactory();
        this.basicDimensionTestDataFactory = new BasicDimensionTestDataFactory();
        this.basicSelectableOptionTestDataFactory = new BasicSelectableOptionTestDataFactory();
        this.weightedDimensionTestDataFactory = new WeightedDimensionTestDataFactory();
        this.weightedSelectableOptionTestDataFactory = new WeightedSelectableOptionTestDataFactory();
        this.multiselectDimensionTestDataFactory = new MultiselectDimensionTestDataFactory();
        this.multiselectSelectableOptionTestDataFactory = new MultiselectSelectableOptionTestDataFactory();
        this.profileDto = factory.buildDto(1);
        this.profileEntity = factory.buildEntity(1);
    }

    @Test
    void workToDo(){
        assertTrue(false);
    }
    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange
        ProfileEntity expected = profileEntity;
        ProfileDto input = profileDto;

        serviceMockHelper(input, expected);

        //Act
        Optional<ProfileEntity> profileEntityOptional = profileMapper.dtoToEntity(profileDto);

        //Assert

        assertEquals(profileEntity, profileEntityOptional.get());
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        //Arrange
        List<ProfileEntity> inputList = Collections.emptyList();

        //Act
        List<ProfileDto> actualList = this.profileMapper.entityToDto(inputList);

        //Assert
        assertTrue(actualList.isEmpty());
    }

    @Test
    void workToDo2(){
        assertTrue(false);
    }
    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        //Arrange
        ProfileDto expectedDto = profileDto;
        ProfileEntity inputEntity = profileEntity;

        serviceMockHelper(expectedDto, inputEntity);

        //Act
        ProfileDto actualDto = this.profileMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        //Arrange
        List<ProfileEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<ProfileDto> expectedDtos = Stream.of(1, 2, 3).map(this.factory::buildDto).toList();



        //Act
        List<ProfileDto> actualDtos = this.profileMapper.entityToDto(inputEntities);

        //Assert
        assertEquals(3, actualDtos.size());
        assertEquals(expectedDtos, actualDtos);
    }
    
    void serviceMockHelper(ProfileDto dto, ProfileEntity entity) {

        when(basicDimensionService.getSelectableOptionById(dto.getProject().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(1)));
        when(basicDimensionService.getSelectableOptionById(dto.getGender().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(2)));
        when(basicDimensionService.getSelectableOptionById(dto.getOriginCountry().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(3)));
        when(basicDimensionService.getSelectableOptionById(dto.getReligion().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(4)));
        when(basicDimensionService.getSelectableOptionById(dto.getMotherTongue().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(5)));
        when(basicDimensionService.getSelectableOptionById(dto.getEducation().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(6)));
        when(basicDimensionService.getSelectableOptionById(dto.getDiet().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(7)));
        when(basicDimensionService.getSelectableOptionById(dto.getSocialBackground().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(8)));
        when(basicDimensionService.getSelectableOptionById(dto.getSocialBackgroundDiscrimination().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(9)));
        when(basicDimensionService.getSelectableOptionById(dto.getSexualOrientation().getId()))
                .thenReturn(entity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(10)));

        when(weightedDimService.getSelectableOptionById(dto.getWorkExperience().getId()))
                .thenReturn(entity.getSelectedWeightedValues().get(weightedDimensionTestDataFactory.buildEntity(1)));

        when(multiselectDimService.getSelectableOptions(dto.getHobby().stream().map(HobbyDto::getId).collect(Collectors.toList())))
                .thenReturn(entity.getSelectedMultiselectValues().get(multiselectDimensionTestDataFactory.buildEntity(1)).getSelectedOptions().stream().toList());

        when(basicDimensionService.getDimension("Projekt")).thenReturn(basicDimensionTestDataFactory.buildEntity(1));
        when(basicDimensionService.getDimension("Geschlechtliche Identität")).thenReturn(basicDimensionTestDataFactory.buildEntity(2));
        when(basicDimensionService.getDimension("Ethnische Herkunft")).thenReturn(basicDimensionTestDataFactory.buildEntity(3));
        when(basicDimensionService.getDimension("Religion")).thenReturn(basicDimensionTestDataFactory.buildEntity(4));
        when(basicDimensionService.getDimension("Muttersprache")).thenReturn(basicDimensionTestDataFactory.buildEntity(5));
        when(basicDimensionService.getDimension("Bildungsweg")).thenReturn(basicDimensionTestDataFactory.buildEntity(6));
        when(basicDimensionService.getDimension("Ernährung")).thenReturn(basicDimensionTestDataFactory.buildEntity(7));
        when(basicDimensionService.getDimension("Soziale Herkunft")).thenReturn(basicDimensionTestDataFactory.buildEntity(8));
        when(basicDimensionService.getDimension("Diskriminierung aufgrund sozialer Herkunft")).thenReturn(basicDimensionTestDataFactory.buildEntity(9));
        when(basicDimensionService.getDimension("Sexuelle Orientierung")).thenReturn(basicDimensionTestDataFactory.buildEntity(10));

        when(weightedDimService.getDimension("Berufserfahrung")).thenReturn(weightedDimensionTestDataFactory.buildEntity(1));

        when(multiselectDimService.getDimension("Hobby")).thenReturn(multiselectDimensionTestDataFactory.buildEntity(1));
    }
}
