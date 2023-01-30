package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfileMapper {

    private final EducationMapper educationMapper;
    private final DietMapper dietMapper;
    private final GenderMapper genderMapper;
    private final LanguageMapper languageMapper;
    private final CountryMapper countryMapper;
    private final ProjectMapper projectMapper;
    private final ReligionMapper religionMapper;
    private final WorkExperienceMapper workExperienceMapper;
    private final HobbyMapper hobbyMapper;
    private final SexualOrientationMapper sexualOrientationMapper;
    private final SocialBackgroundMapper socialBackgroundMapper;
    private final SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;
    private final BasicDimensionService basicDimensionService;
    private final WeightedDimensionService weightedDimensionService;
    private final MultiselectDimensionService multiselectDimensionService;

    public ProfileDto entityToDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setBirthYear(entity.getBirthYear());
        dto.setEducation(educationMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Bildungsweg").get())));
        dto.setDiet(dietMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Ernährung").get())));
        dto.setGender(genderMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Geschlechtliche Identität").get())));
        dto.setHobby(hobbyMapper.entityToDto(List.copyOf(entity.getSelectedMultiselectValues().get(multiselectDimensionService.getDimension("Hobby").get()).getSelectedOptions())));
        dto.setMotherTongue(languageMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Muttersprache").get())));
        dto.setOriginCountry(countryMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Ethnische Herkunft").get())));
        dto.setProject(projectMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Projekt").get())));
        dto.setReligion(religionMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Religion").get())));
        dto.setSexualOrientation(sexualOrientationMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Sexuelle Orientierung").get())));
        dto.setSocialBackground(socialBackgroundMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Soziale Herkunft").get())));
        dto.setSocialBackgroundDiscrimination(socialBackgroundDiscriminationMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Diskriminierung aufgrund sozialer Herkunft").get())));
        dto.setWorkExperience(workExperienceMapper.entityToDto(entity.getSelectedWeightedValues().get(weightedDimensionService.getDimension("Berufserfahrung").get())));

        return dto;
    }

    public ProfileEntity dtoToEntity(ProfileDto dto) {
        Map<BasicDimension, BasicDimensionSelectableOption> selectedBasicOptions = new HashMap<>();

        Map<WeightedDimension, WeightedDimensionSelectableOption> selectedWeightedOptions = new HashMap<>();

        Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> selectedMultiselectOptions = new HashMap<>();

        ProfileEntitySelectedMultiselectValue selectedMultiselectValues = new ProfileEntitySelectedMultiselectValue();
        selectedMultiselectValues.setSelectedOptions(Set.copyOf(multiselectDimensionService.getSelectableOptions(
                dto.getHobby().stream().map(HobbyDto::getId).collect(Collectors.toList()))));
        selectedMultiselectValues.setMultiselectDimension(multiselectDimensionService.getDimension("Hobby").get());

        BasicDimension basicDimension = basicDimensionService.getDimension("Bildungsweg").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getEducation().getId()));
        basicDimension = basicDimensionService.getDimension("Ernährung").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getDiet().getId()));
        basicDimension = basicDimensionService.getDimension("Geschlechtliche Identität").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getGender().getId()));
        basicDimension = basicDimensionService.getDimension("Muttersprache").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getMotherTongue().getId()));
        basicDimension = basicDimensionService.getDimension("Ethnische Herkunft").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getOriginCountry().getId()));
        basicDimension = basicDimensionService.getDimension("Projekt").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getProject().getId()));
        basicDimension = basicDimensionService.getDimension("Religion").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getReligion().getId()));
        basicDimension = basicDimensionService.getDimension("Sexuelle Orientierung").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getSexualOrientation().getId()));
        basicDimension = basicDimensionService.getDimension("Soziale Herkunft").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getSocialBackground().getId()));
        basicDimension = basicDimensionService.getDimension("Diskriminierung aufgrund sozialer Herkunft").get();
        selectedBasicOptions.put(basicDimension, getBasicSelectableOptionOrDefault(basicDimension, dto.getSocialBackgroundDiscrimination().getId()));

        WeightedDimension weightedDimension = weightedDimensionService.getDimension("Berufserfahrung").get();
        selectedWeightedOptions.put(weightedDimension, getWeightedSelectableOptionOrDefault(weightedDimension, dto.getWorkExperience().getId()));

        selectedMultiselectOptions.put(multiselectDimensionService.getDimension("Hobby").get(),
                selectedMultiselectValues);

        return ProfileEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .birthYear(dto.getBirthYear())
                .selectedBasicValues(selectedBasicOptions)
                .selectedWeightedValues(selectedWeightedOptions)
                .selectedMultiselectValues(selectedMultiselectOptions)
                .wasChangedByAdmin(false)
                .build();
    }
    
    private BasicDimensionSelectableOption getBasicSelectableOptionOrDefault(BasicDimension dimension, Long selectableId) {
        var selectableOption = basicDimensionService.getSelectableOptionById(selectableId);
        if (selectableOption.isPresent() && dimension.getDimensionCategory() == selectableOption.get().getDimensionCategory()) {
            return selectableOption.get();
        }
        return dimension.getDefaultValue();
    }
    
    private WeightedDimensionSelectableOption getWeightedSelectableOptionOrDefault(WeightedDimension dimension, Long selectableId) {
        var selectableOption = weightedDimensionService.getSelectableOptionById(selectableId);
        if (selectableOption.isPresent() && dimension.getDimensionCategory() == selectableOption.get().getDimensionCategory()) {
            return selectableOption.get();
        }
        return dimension.getDefaultValue();
    }

    private boolean allObjectWithIdsArePresent(Optional<?>... optionals) {
        return Arrays.stream(optionals).allMatch(Optional::isPresent);
    }

    public List<ProfileDto> entityToDto(List<ProfileEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}