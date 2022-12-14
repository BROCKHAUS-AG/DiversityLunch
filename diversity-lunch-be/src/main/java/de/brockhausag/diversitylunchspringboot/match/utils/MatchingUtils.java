package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.match.records.ScoreAndCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.brockhausag.diversitylunchspringboot.match.utils.MatchingDefaultDimension.getScoreFromDefaultEntities;
import static de.brockhausag.diversitylunchspringboot.match.utils.MatchingHobbyDimension.compareHobbies;
import static de.brockhausag.diversitylunchspringboot.match.utils.MatchingWeightedDimension.getScoreFromWeightedEntities;

@Slf4j
@UtilityClass
public class MatchingUtils {
    private static final Random random = new Random();

    public static ScoreAndCategory getCurrentScore(ProfileEntity profile1, ProfileEntity profile2) {
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
}
