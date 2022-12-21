/*
package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class MatchingHobbyDimension {
    public static final int STANDARD_SCORE_BY_DIFFERENCE = 3;

    static int compareHobbies(ProfileEntity profile1, ProfileEntity profile2, List<Category> potentialQuestionsCategories) {
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
}
*/
