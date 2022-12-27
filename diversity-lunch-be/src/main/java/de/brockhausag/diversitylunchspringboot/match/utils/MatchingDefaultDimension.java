package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;

@UtilityClass
public class MatchingDefaultDimension {
    public static final int STANDARD_SCORE_BY_DIFFERENCE = 3;
    private static final int EQUAL_SCORE = 0;

    static int getScoreFromDefaultDimensions(ProfileEntity profile1, ProfileEntity profile2, List<DimensionCategory> potentialQuestionsCategories) {
        int currentScore = 0;

        Set<BasicDimension> basicDimensionSet = profile1.getSelectedBasicValues().keySet();

        int entityScore;
        for (BasicDimension dimension : basicDimensionSet) {
            BasicDimensionSelectableOption option1 = profile1.getSelectedBasicValues().get(dimension);
            BasicDimensionSelectableOption option2 = profile2.getSelectedBasicValues().get(dimension);

            if (option1.isIgnoreInScoring() || option2.isIgnoreInScoring()) {
                continue;
            }
            entityScore = compareDefaultDimensionOptions(option1, option2);

            if (entityScore != EQUAL_SCORE) {
                potentialQuestionsCategories.add(option1.getDimensionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }

    private int compareDefaultDimensionOptions(BasicDimensionSelectableOption option1, BasicDimensionSelectableOption option2) {
        if (option1.getId().equals(option2.getId())) {
            return EQUAL_SCORE;
        }
        return STANDARD_SCORE_BY_DIFFERENCE;
    }
}
