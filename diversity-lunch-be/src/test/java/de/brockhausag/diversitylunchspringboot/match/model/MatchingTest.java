package de.brockhausag.diversitylunchspringboot.match.model;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategoryRepository;
import de.brockhausag.diversitylunchspringboot.match.records.ScoreAndCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchingTest {

    private ProfileEntity profile1;
    private ProfileEntity profile2;
    private ProfileEntity profile3;
    @Mock
    private Random random;
    @Mock
    private DimensionCategoryRepository categoryRepository;
    @InjectMocks
    private Matching match;

    @BeforeEach
    void setup() {
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        profile1 = profileTestdataFactory.buildEntity(1);
        profile2 = profileTestdataFactory.buildEntity(2);
        profile3 = profileTestdataFactory.buildEntity(3);
    }

    @Test
    void testScoreAndCategory_withProfile1AndProfile2_shouldReturnScore2AndCategoryDiet() {
        // Arrange
        match = new Matching(random, categoryRepository,
                MeetingProposalEntity.builder()
                    .proposerProfile(profile1)
                    .build(),
                MeetingProposalEntity.builder()
                    .proposerProfile(profile2)
                    .build());
        ScoreAndCategory expected = new ScoreAndCategory(16,
                DimensionCategory.builder()
                    .id(1L)
                    .description("Geschlecht")
                    .profileQuestion("Geschlecht?")
                    .build());
        when(random.nextInt(anyInt())).thenReturn(0);

        // Act
        ScoreAndCategory actual = match.getStats();


        // Assert
        assertEquals(expected.currentScore(), actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }
/*
    @ParameterizedTest(name = "{index} => birthYear={0}, expectedScore={1}, expectedCategoryIdentifier={2}")
    @CsvSource({
            "1971, 2, DIET",
            "1977, 3, AGE",
    })
    void testCurrentScoreBirthYear(int birthYear, int expectedScore, String expectedCategoryIdentifier) {
        profile2.setBirthYear(birthYear);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expectedScore, actual.currentScore());
        assertEquals(getCategoryFromIdentifier(expectedCategoryIdentifier), actual.category());
    }

    @Test
    void testCurrentScoreBirthYear() {
        profile2.setBirthYear(1961);
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(1, actual.currentScore());
        assertSame(actual.category(), Category.DIET);
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, experienceWeight={1}, expectedScore={2}, expectedCategoryIdentifier={3}")
    @CsvSource({
            "0-3 Jahre, 1, 1, DIET",
            "4-10 Jahre, 2, 2, WORK_EXPERIENCE",
            "über 10 Jahre, 3, 3, WORK_EXPERIENCE"
    })
    void testCurrentScoreLowWorkExperienceProfile2(String workExperience, int experienceWeight, int expectedScore, String expectedCategoryIdentifier) {
        profile2.getWorkExperience().setDescriptor(workExperience);
        profile2.getWorkExperience().setWeight(experienceWeight);
        ScoreAndCategory expected = new ScoreAndCategory(expectedScore, getCategoryFromIdentifier(expectedCategoryIdentifier));
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expectedScore, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, experienceWeight={1}, expectedScore={2}, expectedCategoryIdentifier={3}")
    @CsvSource({
            "0-3 Jahre, 1, 1, DIET",
            "4-10 Jahre, 2, 2, WORK_EXPERIENCE",
            "über 10 Jahre, 3, 3, WORK_EXPERIENCE"
    })
    void testCurrentScoreLowWorkExperienceProfile1(String workExperience, int experienceWeight, int expectedScore, String expectedCategoryIdentifier) {
        profile1.getWorkExperience().setDescriptor(workExperience);
        profile1.getWorkExperience().setWeight(experienceWeight);
        ScoreAndCategory expected = new ScoreAndCategory(expectedScore, getCategoryFromIdentifier(expectedCategoryIdentifier));
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expectedScore, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, experienceWeight={1}, expectedScore={2}, expectedCategoryIdentifier={3}")
    @CsvSource({
            "0-3 Jahre, 1, 2, WORK_EXPERIENCE",
            "4-10 Jahre, 2, 1, DIET",
            "über 10 Jahre, 3, 2, WORK_EXPERIENCE"
    })
    void testCurrentScoreMidWorkExperienceProfile2(String workExperience, int experienceWeight, int expectedScore, String expectedCategoryIdentifier) {
        profile1.getWorkExperience().setDescriptor(workExperience);
        profile1.getWorkExperience().setWeight(experienceWeight);
        profile2.getWorkExperience().setDescriptor("4-10 Jahre");
        profile2.getWorkExperience().setWeight(2);
        ScoreAndCategory expected = new ScoreAndCategory(expectedScore, getCategoryFromIdentifier(expectedCategoryIdentifier));
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expectedScore, actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest(name = "{index} => workExperience={0}, experienceWeight={1}, expectedScore={2}, expectedCategoryIdentifier={3}")
    @CsvSource({
            "0-3 Jahre, 1, 2, WORK_EXPERIENCE",
            "4-10 Jahre, 2, 1, DIET",
            "über 10 Jahre, 3, 2, WORK_EXPERIENCE"
    })
    void testCurrentScoreMidWorkExperienceProfile21(String workExperience, int experienceWeight, int expectedScore, String expectedCategoryIdentifier) {
        profile2.getWorkExperience().setDescriptor(workExperience);
        profile2.getWorkExperience().setWeight(experienceWeight);
        profile1.getWorkExperience().setDescriptor("4-10 Jahre");
        profile1.getWorkExperience().setWeight(2);
        ScoreAndCategory expected = new ScoreAndCategory(expectedScore, getCategoryFromIdentifier(expectedCategoryIdentifier));
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile2);
        assertEquals(expectedScore, actual.currentScore());
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
        int expected = 32;
        ScoreAndCategory actual = MatchingUtils.getCurrentScore(profile1, profile3);
        assertEquals(expected, actual.currentScore());
        assertNotNull(actual.category());
    }

    @Test
    void testCurrentScoreProfileFor_KeineAngabe_InGender_ShouldHave_30_Points_With_different_profiles() {
        profile3.getGender().setDescriptor("Keine Angabe");
        profile1.getGender().setDescriptor("Männlich");
        final int expectedScore = 29;
        int actualScore;

        actualScore = MatchingUtils.getCurrentScore(profile1, profile3).currentScore();

        assertEquals(expectedScore, actualScore);
    }

    @Test
    void testScoreBetweenDifferentHobbies_1_different_hobby_shouldReturn2() {
        HobbyEntity hobby1 = hobbyTestDataFactory.buildEntity(1);
        HobbyEntity hobby2 = hobbyTestDataFactory.buildEntity(2);

        when(mockedProfile.getHobby()).thenReturn(List.of(hobby1, hobby2));
        when(mockedProfile.getBirthYear()).thenReturn(profile2.getBirthYear());

        // birth year gives a lowest score of 1 so our score will be 2
        int expected = 2;

        int actualScore = MatchingUtils.getCurrentScore(mockedProfile, profile2).currentScore();

        assertEquals(expected, actualScore);
    }

    @Test
    void testCurrentScoreProfileFor_KeineAngabe_InGender_ShouldHave_1_Points_With_equal_profiles() {
        profile2.getGender().setDescriptor("Keine Angabe");
        profile1.getGender().setDescriptor("Männlich");
        final int expectedScore = 1;
        int actualScore;

        actualScore = MatchingUtils.getCurrentScore(profile1, profile2).currentScore();

        assertEquals(expectedScore, actualScore);
    }

    private Category getCategoryFromIdentifier(String categoryIdentifier) {
        return switch (categoryIdentifier) {
            case "DIET" -> Category.DIET;
            case "HOBBY" -> Category.HOBBY;
            case "WORK_EXPERIENCE" -> Category.WORK_EXPERIENCE;
            case "AGE" -> Category.AGE;
            default -> null;
        };
    }*/
}
