package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class MatchingMultiselectDimension {
    public static final int STANDARD_SCORE_BY_DIFFERENCE = 3;

    static int getScoreFromMultiselectDimensions(ProfileEntity profile1, ProfileEntity profile2, List<DimensionCategory> potentialQuestionsCategories) {
        int currentScore = 0;

        Set<MultiselectDimension> multiselectDimensionSet = profile1.getSelectedMultiselectValues().keySet();

        for (MultiselectDimension dimension : multiselectDimensionSet) {
            Set<MultiselectDimensionSelectableOption> optionList1 = new HashSet<>(profile1.getSelectedMultiselectValues().get(dimension).getSelectedOptions());
            Set<MultiselectDimensionSelectableOption> optionList2 = new HashSet<>(profile2.getSelectedMultiselectValues().get(dimension).getSelectedOptions());

            if(optionList1.size() == 0 || optionList2.size() == 0){
                return  0;
            }

            int totalOptionsBetweenBoth = optionList1.size() + optionList2.size();
            int commonOptions = optionList1.size();
            optionList1.removeAll(optionList2);
            commonOptions -= optionList1.size();

            int differentOptions = totalOptionsBetweenBoth - 2 * commonOptions;

            double ratio = (double) differentOptions / totalOptionsBetweenBoth;

            if (ratio > 0) {
                potentialQuestionsCategories.add(dimension.getDimensionCategory());
            }
            currentScore += (int) Math.round(ratio * STANDARD_SCORE_BY_DIFFERENCE);
        }
        return currentScore;
    }
}
