package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntity;
import de.brockhausag.diversitylunchspringboot.generics.WeightedDimension.WeightedEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import liquibase.pro.packaged.L;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@UtilityClass
public class MatchingUtils {

    private static final Random random = new Random();
    public static final int STANDARD_SCORE_BY_DIFFERENCE = 3;


    public ScoreAndCategory getCurrentScore(ProfileEntity profile1, ProfileEntity profile2) {
        List<Category> potentialQuestionsCategories = new ArrayList<>();

        int currentScore = 0;
        //First: Score Base Entities
        currentScore += getScoreFromBaseEntities(profile1, profile2, potentialQuestionsCategories);
        //Second Score Weighted Entities
        currentScore += getScoreFromWeightedEntities(profile1,profile2,potentialQuestionsCategories);
        //Third Score Birthdate and hobby or miscellaneous
        currentScore += compareBirthYear(profile1, profile2, potentialQuestionsCategories);
        currentScore += compareHobbies(profile1, profile2, potentialQuestionsCategories);

        int randomIndex = random.nextInt(potentialQuestionsCategories.size());

        return new ScoreAndCategory(currentScore, potentialQuestionsCategories.get(randomIndex));
    }

    private static int compareHobbies(ProfileEntity profile1, ProfileEntity profile2,List<Category> potentialQuestionsCategories ) {
        if(profile1.getHobby().getQuestionCategory() == profile2.getHobby().getQuestionCategory()){
            return 0;
        }
        potentialQuestionsCategories.add(Category.HOBBY);
        return STANDARD_SCORE_BY_DIFFERENCE;
    }

    private static int getScoreFromBaseEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        int currentScore = 0;

        List<BaseEntity> baseEntitiesProfile1 = profile1.getBaseEntities();
        List<BaseEntity> baseEntitiesProfile2 = profile2.getBaseEntities();

        int entityScore;
        for (int i = 0; i < baseEntitiesProfile1.size(); i++) {
            entityScore = compareBaseEntities(baseEntitiesProfile1.get(i), baseEntitiesProfile2.get(i));

            if (entityScore != 0) {
                potentialQuestionsCategories.add(baseEntitiesProfile1.get(i).getQuestionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }
    private static int getScoreFromWeightedEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        int currentScore = 0;

        List<WeightedEntity> weightedEntity1 = profile1.getWeightedEntities();
        List<WeightedEntity> weightedEntity2 = profile2.getWeightedEntities();

        int entityScore;
        for (int i = 0; i < weightedEntity1.size(); i++) {
            entityScore = compareWeightedEntities(weightedEntity1.get(i), weightedEntity2.get(i));

            if (entityScore != 0) {
                potentialQuestionsCategories.add(weightedEntity1.get(i).getQuestionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }

    private int compareWeightedEntities(WeightedEntity weightedEntity1, WeightedEntity weightedEntity2) {
        int weight1 = weightedEntity1.getWeight();
        int weight2 = weightedEntity2.getWeight();

        if (weight1 == 0 || weight2 == 0) {
            return 0;
        }
        return Math.abs(weight1 - weight2);
    }

    private int compareBirthYear(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        final int TWENTY_YEARS_DIFFERENCE = 20;
        final int TEN_YEARS_DIFFERENCE = 10;


        int ageGap = Math.abs(profile1.getBirthYear() - profile2.getBirthYear());
        if(ageGap < TEN_YEARS_DIFFERENCE) {
            return 1;
        } else if(ageGap < TWENTY_YEARS_DIFFERENCE){
            return 2;
        }
        potentialQuestionsCategories.add(Category.AGE);
        return 3;
    }

    private int compareBaseEntities(BaseEntity entity1, BaseEntity entity2) {
        if (entity1.getId() == entity2.getId()){
            return 0;
        }
        return STANDARD_SCORE_BY_DIFFERENCE;
    }
}
