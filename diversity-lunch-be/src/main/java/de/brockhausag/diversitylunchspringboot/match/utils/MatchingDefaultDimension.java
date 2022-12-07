package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MatchingDefaultDimension {
    public static final int STANDARD_SCORE_BY_DIFFERENCE = 3;
    public static final String KEINE_ANGABE = "keine angabe";
    private static final int EQUAL_SCORE = 0;

    static int getScoreFromDefaultEntities(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
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
            entityScore = compareDefaultDimensionEntities(entity1, entity2);

            if (entityScore != EQUAL_SCORE) {
                potentialQuestionsCategories.add(entity1.getQuestionCategory());
            }

            currentScore += entityScore;
        }
        return currentScore;
    }

    private int compareDefaultDimensionEntities(DefaultDimensionEntity entity1, DefaultDimensionEntity entity2) {
        if (entity1.getId().equals(entity2.getId())) {
            return EQUAL_SCORE;
        }
        return STANDARD_SCORE_BY_DIFFERENCE;
    }

    private static boolean entityShouldBeIgnored(DefaultDimensionEntity entity) {
        return entity.getDescriptor().equalsIgnoreCase(KEINE_ANGABE);
    }
}
