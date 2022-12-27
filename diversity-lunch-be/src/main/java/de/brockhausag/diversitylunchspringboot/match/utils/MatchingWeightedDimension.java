package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;

@UtilityClass
public class MatchingWeightedDimension {
    private static final int EQUAL_SCORE = 0;

    static int getScoreFromWeightedDimensions(ProfileEntity profile1, ProfileEntity profile2, List<DimensionCategory> potentialQuestionsCategories) {
        int currentScore = 0;

        Set<WeightedDimension> weightedDimensionSet = profile1.getSelectedWeightedValues().keySet();

        int entityScore;
        for (WeightedDimension dimension : weightedDimensionSet) {
            WeightedDimensionSelectableOption option1 = profile1.getSelectedWeightedValues().get(dimension);
            WeightedDimensionSelectableOption option2 = profile2.getSelectedWeightedValues().get(dimension);

            entityScore = compareWeightedEntities(option1, option2);

            if (entityScore != EQUAL_SCORE) {
                potentialQuestionsCategories.add(option1.getDimensionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }

    private int compareWeightedEntities(WeightedDimensionSelectableOption option1, WeightedDimensionSelectableOption option2) {
        int weight1 = option1.getWeight();
        int weight2 = option2.getWeight();

        if (weight1 == 0 || weight2 == 0) {
            return EQUAL_SCORE;
        }
        return Math.abs(weight1 - weight2);
    }
}
