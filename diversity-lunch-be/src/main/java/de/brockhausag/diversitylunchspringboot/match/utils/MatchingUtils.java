package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntity;
import de.brockhausag.diversitylunchspringboot.generics.WeightedDimension.WeightedEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@UtilityClass
public class MatchingUtils {

    private static final Random random = new Random();
    public static final int DIFFERENT_BASE_ENTITY_SCORE = 3;


    public ScoreAndCategory getCurrentScore(ProfileEntity profile1, ProfileEntity profile2) {
        List<Category> potentialQuestionsCategories = new ArrayList<>();
        int currentScore = 0;

        List<BaseEntity> baseEntitiesProfile1 = profile1.getBaseEntities();
        List<BaseEntity> baseEntitiesProfile2 = profile2.getBaseEntities();

        int entityScore;
        StreamUtils.zip(baseEntitiesProfile1.stream(), baseEntitiesProfile2.stream(), (baseEntity1, baseEntity2) -> {


            // TODO: CATEGORY PER ID AUS DATENBANK ZIEHEN NICHT MEHR HARDCODEN
                entityScore = compareBaseEntities(baseEntity1, baseEntity2);
                if (entityScore != 0){
                    potentialQuestionsCategories.add(baseEntity1.)
                }


                }).forEach(unused->{});
            when

        //GeschlechtPunkte
        currentScore += compareBaseEntities(profile1.getGender().getDescriptor(), profile2.getGender().getDescriptor());
        //KundePunkte
        currentScore += compareBaseEntities(profile1.getProject().getDescriptor(), profile2.getProject().getDescriptor());
        //HerkunftslandPunkte
        currentScore += compareBaseEntities(profile1.getOriginCountry().getDescriptor(), profile2.getOriginCountry().getDescriptor());
        //MuttersprachePunkte
        currentScore += compareBaseEntities(profile1.getMotherTongue().getDescriptor(), profile2.getMotherTongue().getDescriptor());
        //HobbyPunkte
        currentScore += compareBaseEntities(profile1.getHobby().getCategory().getDescriptor(), profile2.getHobby().getCategory().getDescriptor());
        //ReligionsPunkte
        currentScore += compareBaseEntities(profile1.getReligion().getDescriptor(), profile2.getReligion().getDescriptor());
        //BildungswegPunkte
        currentScore += compareBaseEntities(profile1.getEducation().getDescriptor(), profile2.getEducation().getDescriptor());
        //ErnährungsweisePunkte
        currentScore += compareBaseEntities(profile1.getDiet().getDescriptor(), profile2.getDiet().getDescriptor());
        // Sexuelle orienteirung
        currentScore += compareBaseEntities(profile1.getSexualOrientation().getDescriptor(), profile2.getSexualOrientation().getDescriptor());
        // Social Background
        currentScore += compareBaseEntities(profile1.getSocialBackground().getDescriptor(), profile2.getSocialBackground().getDescriptor());
        // Social Background Discrimination
        currentScore += compareBaseEntities(profile1.getSocialBackgroundDiscrimination().getDescriptor(), profile2.getSocialBackgroundDiscrimination().getDescriptor());

        currentScore = getScoreAndAddCategoriesForBirthYearAndWorkExperience(currentScore, potentialQuestionsCategories, profile1, profile2);

        int randomIndex = random.nextInt(potentialQuestionsCategories.size());

        return new ScoreAndCategory(currentScore, potentialQuestionsCategories.get(randomIndex));
    }

    private int getScoreAndAddCategoriesForBirthYearAndWorkExperience(int score, List<Category> categories, ProfileEntity profile1, ProfileEntity profile2) {
        int scoreFromBirthYear = compareBirthYear(profile1, profile2);
        int scoreFromWorkExperience = scoreWeightedEntities(profile1, profile2);

        if (scoreFromBirthYear == 3) {
            categories.add(Category.AGE);
        }
        if (scoreFromWorkExperience == 3) {
            categories.add(Category.WORK_EXPERIENCE);
        }
        if (score == 0) {
            if (scoreFromBirthYear == 2 && scoreFromWorkExperience < 3) {
                categories.add(Category.AGE);
            }
            if (scoreFromBirthYear == 1 && scoreFromWorkExperience < 2) {
                categories.add(Category.AGE);
            }
            if (scoreFromWorkExperience == 2 && scoreFromBirthYear < 3) {
                categories.add(Category.WORK_EXPERIENCE);
            }
            if (scoreFromWorkExperience == 1 && scoreFromBirthYear < 2) {
                categories.add(Category.WORK_EXPERIENCE);
            }
        }
        //GeburtsjahrPunkte
        score += scoreFromBirthYear;
        //BerufserfahrungPunkte
        score += scoreFromWorkExperience;
        return score;
    }

    private int scoreWeightedEntities(WeightedEntity weightedEntity1, WeightedEntity weightedEntity2) {
        int weight1 = weightedEntity1.getWeight();
        int weight2 = weightedEntity2.getWeight();

        if (weight1 == 0 || weight2 == 0){
            return 0;
        }
        return Math.abs(weight1- weight2);
    }

    private int compareBirthYear(ProfileEntity profile1, ProfileEntity profile2) {
        int currentScore = 0;
        int i = Math.abs(profile1.getBirthYear() - profile2.getBirthYear());
        if (i > 3 && i <= 10) {
            currentScore = 1;
        } else if ((i < 20) && (i > 10)) {
            currentScore = 2;
        } else if (i >= 20) {
            currentScore = 3;
        }
        return currentScore;
    }

    private int compareBaseEntities(BaseEntity entity1, BaseEntity entity2) {
        if (entity1.getId() == entity2.getId()) {
            return 0;
        }
        return DIFFERENT_BASE_ENTITY_SCORE;
    }
}
