package de.brockhausag.diversitylunchspringboot.meeting.utils;

import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MatchingUtilsTest {

    private final ProfileTestdataFactory factory = new ProfileTestdataFactory();

    public static int[][] dataSetYears() {
        return new int[][]{
                {1971, 3},
                {1977, 4}
        };
    }

    final ProfileEntity profile1 = factory.entity();
    final ProfileEntity profile2 = factory.entity();

    @Test
    void testCurrentScoreStandard() {
        ScoreAndCategory expected = new ScoreAndCategory(1, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expected.currentScore(), actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest
    @MethodSource(value = "dataSetYears")
    void testCurrentScoreBirthYear(int[] data) {
        profile2.setBirthYear(data[0]);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(data[1], actual.currentScore());
        assertEquals(Category.AGE, actual.category());
    }

    @Test
    void testCurrentScoreBirthYear() {
        profile2.setBirthYear(1961);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(2, actual.currentScore());
        assertTrue(actual.category() == Category.AGE || actual.category() == Category.WORK_EXPERIENCE);
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "HIGH_EXPERIENCE, 1",
            "MID_EXPERIENCE, 2",
            "LOW_EXPERIENCE, 3"
    })
    void testCurrentScoreLowWorkExperienceProfile2(String workExperience, int score) {
        profile2.setWorkExperience(WorkExperience.valueOf(workExperience));
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "HIGH_EXPERIENCE, 1",
            "MID_EXPERIENCE, 2",
            "LOW_EXPERIENCE, 3"
    })
    void testCurrentScoreLowWorkExperienceProfile1(String workExperience, int score) {
        profile1.setWorkExperience(WorkExperience.valueOf(workExperience));
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "HIGH_EXPERIENCE, 2",
            "MID_EXPERIENCE, 1",
            "LOW_EXPERIENCE, 2"
    })
    void testCurrentScoreMidWorkExperienceProfile2(String workExperience, int score) {
        profile1.setWorkExperience(WorkExperience.valueOf(workExperience));
        profile2.setWorkExperience(WorkExperience.MID_EXPERIENCE);
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "HIGH_EXPERIENCE, 2",
            "MID_EXPERIENCE, 1",
            "LOW_EXPERIENCE, 2"
    })
    void testCurrentScoreMidWorkExperienceProfile21(String workExperience, int score) {
        profile2.setWorkExperience(WorkExperience.valueOf(workExperience));
        profile1.setWorkExperience(WorkExperience.MID_EXPERIENCE);
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @Test
    void testCurrentScoreProfileAttributeNotEquals() {
        profile2.setDiet(Diet.VEGAN);
        int expected = 4;
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expected, actual.currentScore());
        assertEquals(Category.DIET, actual.category());
    }

    @Test
    void testCurrentScoreProfileAttribute() {
        profile2.setBirthYear(1990);
        profile2.setCurrentProject(Project.ExampleCompany2);
        profile2.setGender(Gender.FEMALE);
        profile2.setOriginCountry(Country.SPANIEN);
        profile2.setMotherTongue(Language.SPANISCH);
        profile2.setReligion(Religion.OTHER);
        profile2.setHobby(Hobby.ART);
        profile2.setEducation(Education.OTHER);
        profile2.setWorkExperience(WorkExperience.LOW_EXPERIENCE);
        profile2.setDiet(Diet.OTHER);
        int expected = 30;
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expected, actual.currentScore());
        assertTrue(actual.category() == Category.AGE ||
                actual.category() == Category.WORK_EXPERIENCE ||
                actual.category() == Category.DIET ||
                actual.category() == Category.COUNTRY_OF_ORIGIN ||
                actual.category() == Category.CUSTOMER ||
                actual.category() == Category.EDUCATION ||
                actual.category() == Category.HOBBY ||
                actual.category() == Category.MOTHER_TONGUE ||
                actual.category() == Category.RELIGION ||
                actual.category() == Category.GENDER
                );

    }

}
