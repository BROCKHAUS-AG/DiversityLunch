package de.brockhausag.diversitylunchspringboot.match.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.DimensionCategoryRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.ProfileEntitySelectedMultiselectValue;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.match.records.ScoreAndCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Getter
public class Matching {
    private static final int STANDARD_SCORE_BY_DIFFERENCE = 3;
    private static final int EQUAL_SCORE = 0;
    private final Random random;
    @Getter(AccessLevel.NONE)
    private final DimensionCategoryRepository categoryRepository;
    private final MeetingProposalEntity firstProposal;
    private final MeetingProposalEntity secondProposal;
    private ScoreAndCategory stats;

    private void getCurrentScore() {
        List<DimensionCategory> potentialQuestionsCategories = new ArrayList<>();

        int currentScore = 0;
        //First: Score Default Entities
        currentScore += getScoreFromBasicDimensions(potentialQuestionsCategories);
        //Second: Score Weighted Entities
        currentScore += getScoreFromWeightedDimensions(potentialQuestionsCategories);
        //Third: Score Multiselect Entities
        currentScore += getScoreFromMultiselectDimensions(potentialQuestionsCategories);
        //Fourth: Score Birthdate or miscellaneous
        currentScore += compareBirthYear(potentialQuestionsCategories);

        DimensionCategory category;
        if (potentialQuestionsCategories.size() > 0) {
            int randomIndex = random.nextInt(potentialQuestionsCategories.size());
            category = potentialQuestionsCategories.get(randomIndex);
        } else {
            // Es besteht kein Matching zwischen exakt übereinstimmenden Profilen
            // Deshalb werden keine Fragen an die Profile gestellt
            // Diät wurde als neutrale Kategorie ausgewählt
            category = categoryRepository.getDimensionCategoryByDescription("Ernährung").orElseThrow();
        }

        stats = new ScoreAndCategory(currentScore, category);
    }

    private int getScoreFromBasicDimensions(List<DimensionCategory> potentialQuestionsCategories) {
        int currentScore = 0;
        ProfileEntity firstProfile = firstProposal.getProposerProfile();
        ProfileEntity secondProfile = secondProposal.getProposerProfile();

        Set<BasicDimension> basicDimensionSet = firstProfile.getSelectedBasicValues().keySet();

        int entityScore;
        for (BasicDimension dimension : basicDimensionSet) {
            BasicDimensionSelectableOption option1 = firstProfile.getSelectedBasicValues().get(dimension);
            BasicDimensionSelectableOption option2 = secondProfile.getSelectedBasicValues().get(dimension);

            if (option1 == null || option1.isIgnoreInScoring() || option2 == null || option2.isIgnoreInScoring()) {
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

    private static int compareDefaultDimensionOptions(BasicDimensionSelectableOption option1, BasicDimensionSelectableOption option2) {
        if (option1.getId().equals(option2.getId())) {
            return EQUAL_SCORE;
        }
        return STANDARD_SCORE_BY_DIFFERENCE;
    }

    private int getScoreFromWeightedDimensions(List<DimensionCategory> potentialQuestionsCategories) {
        int currentScore = 0;
        ProfileEntity firstProfile = firstProposal.getProposerProfile();
        ProfileEntity secondProfile = secondProposal.getProposerProfile();

        Set<WeightedDimension> weightedDimensionSet = firstProfile.getSelectedWeightedValues().keySet();

        int entityScore;
        for (WeightedDimension dimension : weightedDimensionSet) {
            WeightedDimensionSelectableOption option1 = firstProfile.getSelectedWeightedValues().get(dimension);
            WeightedDimensionSelectableOption option2 = secondProfile.getSelectedWeightedValues().get(dimension);

            if (option1 == null || option1.isIgnoreInScoring() || option2 == null || option2.isIgnoreInScoring()) {
                continue;
            }
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

    private int getScoreFromMultiselectDimensions(List<DimensionCategory> potentialQuestionsCategories) {
        int currentScore = 0;
        ProfileEntity firstProfile = firstProposal.getProposerProfile();
        ProfileEntity secondProfile = secondProposal.getProposerProfile();

        Set<MultiselectDimension> multiselectDimensionSet = firstProfile.getSelectedMultiselectValues().keySet();

        for (MultiselectDimension dimension : multiselectDimensionSet) {
            ProfileEntitySelectedMultiselectValue option1 = firstProfile.getSelectedMultiselectValues().get(dimension);
            ProfileEntitySelectedMultiselectValue option2 = secondProfile.getSelectedMultiselectValues().get(dimension);
            if (option1 == null || option2 == null)
                continue;
            Set<MultiselectDimensionSelectableOption> optionList1 = new HashSet<>(option1.getSelectedOptions());
            Set<MultiselectDimensionSelectableOption> optionList2 = new HashSet<>(option2.getSelectedOptions());

            if(optionList1.size() == 0 || optionList2.size() == 0){
                continue;
            }

            int totalOptionsBetweenBoth = optionList1.size() + optionList2.size();
            int commonOptions = optionList1.size();
            optionList1.removeAll(optionList2);
            commonOptions -= optionList1.size();

            int differentOptions = totalOptionsBetweenBoth - 2 * commonOptions;

            double ratio = (double) differentOptions / totalOptionsBetweenBoth;

            if (ratio != EQUAL_SCORE) {
                potentialQuestionsCategories.add(dimension.getDimensionCategory());
            }
            currentScore += (int) Math.round(ratio * STANDARD_SCORE_BY_DIFFERENCE);
        }
        return currentScore;
    }

    private int compareBirthYear(List<DimensionCategory> potentialQuestionsCategories) {
        final int TWENTY_YEARS_DIFFERENCE = 20;
        final int TEN_YEARS_DIFFERENCE = 10;

        final int LOWEST_SCORE = 1;
        final int MIDDLE_SCORE = 2;
        final int HIGHEST_SCORE = 3;

        int ageGap = Math.abs(firstProposal.getProposerProfile().getBirthYear() - secondProposal.getProposerProfile().getBirthYear());
        if (ageGap < TEN_YEARS_DIFFERENCE) {
            return LOWEST_SCORE;
        } else if (ageGap < TWENTY_YEARS_DIFFERENCE) {
            return MIDDLE_SCORE;
        }

        potentialQuestionsCategories.add(categoryRepository.getDimensionCategoryByDescription("Alter").orElseThrow());
        return HIGHEST_SCORE;
    }

    public ScoreAndCategory getStats() {
        getCurrentScore();
        return stats;
    }
}
