/*
package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MatchingWeightedDimension {
    private static final int EQUAL_SCORE = 0;

    static int getScoreFromWeightedEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
        int currentScore = 0;

        return 1;

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
}
*/
