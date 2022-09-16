package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper implements Mapper<ProfileDto, ProfileEntity> {

    private final EducationMapper educationMapper;
    private final DietMapper dietMapper;
    private final GenderMapper genderMapper;
    private final LanguageMapper languageMapper;
    private final CountryMapper countryMapper;
    private final ProjectMapper projectMapper;
    private final ReligionMapper religionMapper;

    public ProfileMapper(EducationMapper educationMapper,
                         DietMapper dietMapper,
                         GenderMapper genderMapper,
                         LanguageMapper languageMapper,
                         CountryMapper countryMapper,
                         ProjectMapper projectMapper,
                         ReligionMapper religionMapper) {
        this.educationMapper = educationMapper;
        this.dietMapper = dietMapper;
        this.genderMapper = genderMapper;
        this.languageMapper = languageMapper;
        this.countryMapper = countryMapper;
        this.projectMapper = projectMapper;
        this.religionMapper = religionMapper;
    }

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

        dto.setHobby(entity.getHobby());
        dto.setWorkExperience(entity.getWorkExperience());
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

        entity.setHobby(dto.getHobby());
        entity.setWorkExperience(dto.getWorkExperience());
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