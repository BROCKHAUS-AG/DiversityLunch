package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper implements Mapper<ProfileDto, ProfileEntity> {

    private final EducationMapper educationMapper = new EducationMapper();
    private final DietMapper dietMapper = new DietMapper();
    private final GenderMapper genderMapper = new GenderMapper();
    private final LanguageMapper languageMapper= new LanguageMapper();
    private final CountryMapper countryMapper = new CountryMapper();
    private final ProjectMapper projectMapper = new ProjectMapper();
    private final ReligionMapper religionMapper = new ReligionMapper();
    private final WorkExperienceMapper workExperienceMapper = new WorkExperienceMapper();
    private final HobbyMapper hobbyMapper = new HobbyMapper();


    @Override
    public ProfileDto entityToDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setBirthYear(entity.getBirthYear());

        dto.setEducation(educationMapper.entityToDto(entity.getEducation()));
        dto.setDiet(dietMapper.entityToDto(entity.getDiet()));
        dto.setGender(genderMapper.entityToDto(entity.getGender()));
        dto.setMotherTongue(languageMapper.entityToDto(entity.getMotherTongue()));
        dto.setOriginCountry(countryMapper.entityToDto(entity.getOriginCountry()));
        dto.setProjects(projectMapper.entityToDto(entity.getProjects()));
        dto.setReligion(religionMapper.entityToDto(entity.getReligion()));
        dto.setWorkExperience(workExperienceMapper.entityToDto(entity.getWorkExperience()));

        dto.setHobby(hobbyMapper.entityToDto(entity.getHobby()));

        return dto;
    }

    @Override
    public ProfileEntity dtoToEntity(ProfileDto dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setBirthYear(dto.getBirthYear());

        entity.setEducation(educationMapper.dtoToEntity(dto.getEducation()));
        entity.setDiet(dietMapper.dtoToEntity(dto.getDiet()));
        entity.setGender(genderMapper.dtoToEntity(dto.getGender()));
        entity.setMotherTongue(languageMapper.dtoToEntity(dto.getMotherTongue()));
        entity.setOriginCountry(countryMapper.dtoToEntity(dto.getOriginCountry()));
        entity.setProjects(projectMapper.dtoToEntity(dto.getProjects()));
        entity.setReligion(religionMapper.dtoToEntity(dto.getReligion()));
        entity.setWorkExperience(workExperienceMapper.dtoToEntity(dto.getWorkExperience()));

        entity.setHobby(hobbyMapper.dtoToEntity(dto.getHobby()));
        return entity;
    }

    @Override
    public List<ProfileDto> entityToDto(List<ProfileEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProfileEntity> dtoToEntity(List<ProfileDto> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}