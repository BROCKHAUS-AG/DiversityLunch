package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntity;
import de.brockhausag.diversitylunchspringboot.generics.WeightedDimension.WeightedEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
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

    private static final int EQUAL_SCORE = 0;


    public ScoreAndCategory getCurrentScore(ProfileEntity profile1, ProfileEntity profile2) {
        List<Category> potentialQuestionsCategories = new ArrayList<>();

        int currentScore = 0;
        //First: Score Base Entities
        currentScore += getScoreFromBaseEntities(profile1, profile2, potentialQuestionsCategories);
        //Second Score Weighted Entities
        currentScore += getScoreFromWeightedEntities(profile1,profile2,potentialQuestionsCategories);
        //Third Score Birthdate or miscellaneous
        currentScore += compareBirthYear(profile1, profile2, potentialQuestionsCategories);

        int randomIndex = random.nextInt(potentialQuestionsCategories.size());

        return new ScoreAndCategory(currentScore, potentialQuestionsCategories.get(randomIndex));
    }


    private static int getScoreFromBaseEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        int currentScore = 0;

        List<BaseEntity> baseEntitiesProfile1 = profile1.getBaseEntities();
        List<BaseEntity> baseEntitiesProfile2 = profile2.getBaseEntities();

        int entityScore;
        for (int i = 0; i < baseEntitiesProfile1.size(); i++) {
            BaseEntity entity1 = baseEntitiesProfile1.get(i);
            BaseEntity entity2 = baseEntitiesProfile2.get(i);

            if (entityShouldBeIgnored(entity1) || entityShouldBeIgnored(entity2)){
                continue;
            }
            entityScore = compareBaseEntities(entity1, entity2);

            if (entityScore != EQUAL_SCORE) {
                potentialQuestionsCategories.add(entity1.getQuestionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }

    private static boolean entityShouldBeIgnored(BaseEntity entity) {
        return entity.getDescriptor().equalsIgnoreCase("keine angabe");
    }

    private static int getScoreFromWeightedEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        int currentScore = 0;

        List<WeightedEntity> weightedEntities1 = profile1.getWeightedEntities();
        List<WeightedEntity> weightedEntities2 = profile2.getWeightedEntities();

        int entityScore;
        for (int i = 0; i < weightedEntities1.size(); i++) {
            WeightedEntity weightedEntity1 = weightedEntities1.get(i);
            WeightedEntity weightedEntity2 = weightedEntities2.get(i);

            entityScore = compareWeightedEntities(weightedEntity1, weightedEntity2);

            if (entityScore != EQUAL_SCORE) {
                potentialQuestionsCategories.add(weightedEntity1.getQuestionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }

    private int compareWeightedEntities(WeightedEntity weightedEntity1, WeightedEntity weightedEntity2) {
        int weight1 = weightedEntity1.getWeight();
        int weight2 = weightedEntity2.getWeight();

        if (weight1 == 0 || weight2 == 0) {
            return EQUAL_SCORE;
        }
        return Math.abs(weight1 - weight2);
    }

    private int compareBirthYear(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        final int TWENTY_YEARS_DIFFERENCE = 20;
        final int TEN_YEARS_DIFFERENCE = 10;

        final int LOWEST_SCORE = 1;
        final int MIDDLE_SCORE = 2;
        final int HIGHEST_SCORE = 3;

        int ageGap = Math.abs(profile1.getBirthYear() - profile2.getBirthYear());
        if(ageGap < TEN_YEARS_DIFFERENCE) {
            return LOWEST_SCORE;
        } else if(ageGap < TWENTY_YEARS_DIFFERENCE){
            return MIDDLE_SCORE;
        }
        potentialQuestionsCategories.add(Category.AGE);
        return HIGHEST_SCORE;
    }


    private int compareBaseEntities(BaseEntity entity1, BaseEntity entity2) {
        if (entity1.getId() == entity2.getId()){
            return EQUAL_SCORE;
        }
        return STANDARD_SCORE_BY_DIFFERENCE;
    }
}
