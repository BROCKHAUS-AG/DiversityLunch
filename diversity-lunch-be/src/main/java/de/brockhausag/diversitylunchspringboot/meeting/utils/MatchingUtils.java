package de.brockhausag.diversitylunchspringboot.meeting.utils;

import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.WorkExperience;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@UtilityClass
public class MatchingUtils {

    private static final Random random = new Random();


    public ScoreAndCategory getCurrentScore(ProfileEntity profile1, ProfileEntity profile2) {
        List<Category> categories = new ArrayList<>();
        int currentScore = 0;
        //GeschlechtPunkte
        currentScore += compareProfileAttr(categories, profile1.getGender().toString(), profile2.getGender().toString(), Category.GENDER);
        //KundePunkte
        currentScore += compareProfileAttr(categories, profile1.getCurrentProject().toString(), profile2.getCurrentProject().toString(), Category.CUSTOMER);
        //HerkunftslandPunkte
        currentScore += compareProfileAttr(categories, profile1.getOriginCountry().toString(), profile2.getOriginCountry().toString(), Category.COUNTRY_OF_ORIGIN);
        //MuttersprachePunkte
        currentScore += compareProfileAttr(categories, profile1.getMotherTongue().toString(), profile2.getMotherTongue().toString(), Category.MOTHER_TONGUE);
        //HobbyPunkte
        currentScore += compareProfileAttr(categories, profile1.getHobby().getCategory(), profile2.getHobby().getCategory(), Category.HOBBY);
        //ReligionsPunkte
        currentScore += compareProfileAttr(categories, profile1.getReligion().toString(), profile2.getReligion().toString(), Category.RELIGION);
        //BildungswegPunkte
        currentScore += compareProfileAttr(categories, profile1.getEducation().toString(), profile2.getEducation().toString(), Category.EDUCATION);
        //ErnährungsweisePunkte
        currentScore += compareProfileAttr(categories, profile1.getDiet().toString(), profile2.getDiet().toString(), Category.DIET);

        currentScore = getScoreAndAddCategoriesForBirthYearAndWorkExperience(currentScore, categories, profile1, profile2);

        int randomIndex = random.nextInt(categories.size());

        return new ScoreAndCategory(currentScore, categories.get(randomIndex));
    }

    private int getScoreAndAddCategoriesForBirthYearAndWorkExperience(int score, List<Category> categories, ProfileEntity profile1, ProfileEntity profile2) {
        int scoreFromBirthYear = compareBirthYear(profile1, profile2);
        int scoreFromWorkExperience = compareWorkExperience(profile1, profile2);

        if (scoreFromBirthYear == 3) {
            categories.add(Category.AGE);
        }
        if (scoreFromWorkExperience == 3) {
            categories.add(Category.WORK_EXPERIENCE);
        }
        if (score == 0) {
            if (scoreFromBirthYear == 2 && scoreFromWorkExperience < 3) {
                categories.add(Category.AGE);
            }
            if (scoreFromBirthYear == 1 && scoreFromWorkExperience < 2) {
                categories.add(Category.AGE);
            }
            if (scoreFromWorkExperience == 2 && scoreFromBirthYear < 3) {
                categories.add(Category.WORK_EXPERIENCE);
            }
            if (scoreFromWorkExperience == 1 && scoreFromBirthYear < 2) {
                categories.add(Category.WORK_EXPERIENCE);
            }
        }
        //GeburtsjahrPunkte
        score += scoreFromBirthYear;
        //BerufserfahrungPunkte
        score += scoreFromWorkExperience;
        return score;
    }

    private int compareWorkExperience(ProfileEntity profile1, ProfileEntity profile2) {
        int currentScore;
        if (profile1.getWorkExperience().equals(profile2.getWorkExperience())) {
            currentScore = 1;
        } else if ((profile1.getWorkExperience().equals(WorkExperience.LOW_EXPERIENCE)
                && profile2.getWorkExperience().equals(WorkExperience.HIGH_EXPERIENCE))
                || (profile1.getWorkExperience().equals(WorkExperience.HIGH_EXPERIENCE)
                && profile2.getWorkExperience().equals(WorkExperience.LOW_EXPERIENCE))) {
            currentScore = 3;
        } else {
            currentScore = 2;
        }
        return currentScore;
    }

    private int compareBirthYear(ProfileEntity profile1, ProfileEntity profile2) {
        int currentScore = 0;
        int i = Math.abs(profile1.getBirthYear() - profile2.getBirthYear());
        if (i > 3 && i <= 10) {
            currentScore = 1;
        } else if ((i < 20) && (i > 10)) {
            currentScore = 2;
        } else if (i >= 20) {
            currentScore = 3;
        }
        return currentScore;
    }

    private int compareProfileAttr(List<Category> categories, String attr1, String attr2, Category category) {
        if (!attr1.equals(attr2)) {
            categories.add(category);
            return 3;
        }
        return 0;
    }
}
