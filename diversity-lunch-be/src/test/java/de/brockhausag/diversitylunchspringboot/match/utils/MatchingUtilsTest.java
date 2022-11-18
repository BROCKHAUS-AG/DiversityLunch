package de.brockhausag.diversitylunchspringboot.match.utils;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
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


    public static int[][] dataSetYears() {
        return new int[][]{
                {1971, 3},
                {1977, 4}
        };
    }

    private ProfileEntity profile1;
    private ProfileEntity profile2;
    private ProfileEntity profile3;
    @BeforeEach
    void setup(){
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        profile1 = profileTestdataFactory.buildEntity(1);
        profile2 = profileTestdataFactory.buildEntity(1);
        profile2.setId(2L);
        profile3 = profileTestdataFactory.buildEntity(3);
    }

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
            "0-3 Jahre, 1",
            "4-10 Jahre, 2",
            "über 10 Jahre, 3"
    })
    void testCurrentScoreLowWorkExperienceProfile2(String workExperience, int score) {
        profile2.getWorkExperience().setDescriptor(workExperience);
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "0-3 Jahre, 1",
            "4-10 Jahre, 2",
            "über 10 Jahre, 3"
    })
    void testCurrentScoreLowWorkExperienceProfile1(String workExperience, int score) {
        profile1.getWorkExperience().setDescriptor(workExperience);
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "0-3 Jahre, 2",
            "4-10 Jahre, 1",
            "über 10 Jahre, 2"
    })
    void testCurrentScoreMidWorkExperienceProfile2(String workExperience, int score) {
        profile1.getWorkExperience().setDescriptor(workExperience);
        profile2.getWorkExperience().setDescriptor("4-10 Jahre");
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, score={1}")
    @CsvSource({
            "0-3 Jahre, 2",
            "4-10 Jahre, 1",
            "über 10 Jahre, 2"
    })
    void testCurrentScoreMidWorkExperienceProfile21(String workExperience, int score) {
        profile2.getWorkExperience().setDescriptor(workExperience);
        profile1.getWorkExperience().setDescriptor("4-10 Jahre");
        ScoreAndCategory expected = new ScoreAndCategory(score, Category.WORK_EXPERIENCE);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(score, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @Test
    void testCurrentScoreProfileAttributeNotEquals() {
        int expected = 4;
        profile2.getDiet().setId(2L);
        profile2.getDiet().setDescriptor("second diet");
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expected, actual.currentScore());
        assertEquals(Category.DIET, actual.category());
    }

    @Test
    void testCurrentScoreProfileAttribute() {
        int expected = 30;
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile3);
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
