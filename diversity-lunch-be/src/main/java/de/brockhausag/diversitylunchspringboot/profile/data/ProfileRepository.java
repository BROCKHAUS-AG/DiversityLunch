package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
    List<ProfileEntity> findAllByOriginCountry(CountryEntity entity);
    List<ProfileEntity> findAllByDiet(DietEntity entity);
    List<ProfileEntity> findAllByEducation(EducationEntity entity);
    List<ProfileEntity> findAllByGender(GenderEntity entity);
    List<ProfileEntity> findAllByHobby(HobbyEntity entity);
    List<ProfileEntity> findAllByMotherTongue(LanguageEntity entity);
    List<ProfileEntity> findAllByProject(ProjectEntity entity);
    List<ProfileEntity> findAllByReligion(ReligionEntity entity);
    List<ProfileEntity> findAllBySexualOrientation(SexualOrientationEntity entity);
    List<ProfileEntity> findAllBySocialBackgroundDiscrimination(SocialBackgroundDiscriminationEntity entity);
    List<ProfileEntity> findAllBySocialBackground(SocialBackgroundEntity entity);
    List<ProfileEntity> findAllByWorkExperience(WorkExperienceEntity entity);
}
