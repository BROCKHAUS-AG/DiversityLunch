package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.generics.weightedDimension.WeightedEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@UtilityClass
public class MatchingUtils {
    public static final int STANDARD_SCORE_BY_DIFFERENCE = 3;
    private static final Random random = new Random();
    private static final int EQUAL_SCORE = 0;
    public static final String KEINE_ANGABE = "keine angabe";


    public ScoreAndCategory getCurrentScore(ProfileEntity profile1, ProfileEntity profile2) {
        List<Category> potentialQuestionsCategories = new ArrayList<>();

        int currentScore = 0;
        //First: Score Default Entities
        currentScore += getScoreFromDefaultEntities(profile1, profile2, potentialQuestionsCategories);
        //Second Score Weighted Entities
        currentScore += getScoreFromWeightedEntities(profile1, profile2, potentialQuestionsCategories);
        //Third Score Birthdate or miscellaneous
        currentScore += compareBirthYear(profile1, profile2, potentialQuestionsCategories);
        currentScore += compareHobbies(profile1, profile2, potentialQuestionsCategories);

        Category category;
        if (potentialQuestionsCategories.size() > 0) {
            int randomIndex = random.nextInt(potentialQuestionsCategories.size());
            category = potentialQuestionsCategories.get(randomIndex);
        } else {
            // Es besteht kein Matching zwischen exakt übereinstimmenden Profilen
            // Deshalb werden keine Fragen an die Profile gestellt
            // Diät wurde als neutrale Kategorie ausgewählt
            category = Category.DIET;
        }

        return new ScoreAndCategory(currentScore, category);
    }

    private static int compareHobbies(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        Set<HobbyEntity> hobbies1 = new HashSet<>(profile1.getHobby());
        Set<HobbyEntity> hobbies2 = new HashSet<>(profile2.getHobby());

        if(hobbies1.size() == 0 || hobbies2.size() == 0){
            return  0;
        }

        int totalHobbiesBetweenBoth = hobbies1.size() + hobbies2.size();
        hobbies1.removeAll(hobbies2);
        hobbies2.removeAll(profile1.getHobby());

        // common elements between profile 1 and 2
        hobbies1.addAll(hobbies2);

        int differentHobbies = hobbies1.size();

        double ratio = (double) differentHobbies / totalHobbiesBetweenBoth;

        if (ratio > 0) {
            potentialQuestionsCategories.add(Category.HOBBY);
        }
        return (int) Math.round(ratio * STANDARD_SCORE_BY_DIFFERENCE);
    }


    private static int getScoreFromDefaultEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        int currentScore = 0;

        List<DefaultDimensionEntity> baseEntitiesProfile1 = profile1.getDefaultEntities();
        List<DefaultDimensionEntity> baseEntitiesProfile2 = profile2.getDefaultEntities();

        int entityScore;
        for (int i = 0; i < baseEntitiesProfile1.size(); i++) {
            DefaultDimensionEntity entity1 = baseEntitiesProfile1.get(i);
            DefaultDimensionEntity entity2 = baseEntitiesProfile2.get(i);

            if (entityShouldBeIgnored(entity1) || entityShouldBeIgnored(entity2)) {
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

    private static boolean entityShouldBeIgnored(DefaultDimensionEntity entity) {
        return entity.getDescriptor().equalsIgnoreCase(KEINE_ANGABE);
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
        if (ageGap < TEN_YEARS_DIFFERENCE) {
            return LOWEST_SCORE;
        } else if (ageGap < TWENTY_YEARS_DIFFERENCE) {
            return MIDDLE_SCORE;
        }
        potentialQuestionsCategories.add(Category.AGE);
        return HIGHEST_SCORE;
    }


    private int compareBaseEntities(DefaultDimensionEntity entity1, DefaultDimensionEntity entity2) {
        if (entity1.getId().equals(entity2.getId())) {
            return EQUAL_SCORE;
        }
        return STANDARD_SCORE_BY_DIFFERENCE;
    }
}
