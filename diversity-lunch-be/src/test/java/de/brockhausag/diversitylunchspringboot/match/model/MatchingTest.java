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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MatchingTest {

    private ProfileEntity profile1;
    private ProfileEntity profile2;
    private ProfileEntity profile3;
    private ProfileEntity profileDefault;
    @Mock
    private Random random;
    @Mock
    private DimensionCategoryRepository categoryRepository;
    @InjectMocks
    private Matching match;

    @BeforeEach
    void setup() {
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        profileDefault = profileTestdataFactory.buildEntity(0);
        profile1 = profileTestdataFactory.buildEntity(1);
        profile2 = profileTestdataFactory.buildEntity(2);
        profile3 = profileTestdataFactory.buildEntity(3);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 19, Geschlecht", // Different profiles
            "2, 1, 19, Geschlecht", // Different order of profiles
            "0, 1, 3, Alter",       // Using default profile
            "1, 0, 3, Alter",       // Using default profile as second
            "1, 3, 10, Projekt",    // Profiles with similarities
            "1, 1, 1, Ernährung"    // Same Profiles
    })
    void testScoreAndCategory_withDifferentProfiles_shouldReturnScore16AndCategoryGender(int firstProfile, int secondProfile, int expectedScore, String expectedCategory) {
        // Arrange
        List<ProfileEntity> profiles = new ArrayList<>();
        profiles.add(this.profileDefault);
        profiles.add(this.profile1);
        profiles.add(this.profile2);
        profiles.add(this.profile3);
        match = new Matching(random, categoryRepository,
                MeetingProposalEntity.builder()
                    .proposerProfile(profiles.get(firstProfile))
                    .build(),
                MeetingProposalEntity.builder()
                    .proposerProfile(profiles.get(secondProfile))
                    .build());
        ScoreAndCategory expected = new ScoreAndCategory(expectedScore,
                DimensionCategory.builder()
                    .description(expectedCategory)
                    .build());

        when(random.nextInt(anyInt())).thenReturn(0);
        when(categoryRepository.getDimensionCategoryByDescription("Ernährung")).thenReturn(Optional.ofNullable(DimensionCategory.builder()
                .description("Ernährung")
                .build()));
        when(categoryRepository.getDimensionCategoryByDescription("Alter")).thenReturn(Optional.ofNullable(DimensionCategory.builder()
                .description("Alter")
                .build()));

        // Act
        ScoreAndCategory actual = match.getStats();

        // Assert
        assertEquals(expected.currentScore(), actual.currentScore());
        assertEquals(expected.category().getDescription(), actual.category().getDescription());
    }

    @Test
    void testScoreAndCategory_withFullAndEmptyProfile_shouldReturnScore2AndCategoryDiet() {
        profile2.setSelectedBasicValues(Collections.EMPTY_MAP);
        profile2.setSelectedWeightedValues(Collections.EMPTY_MAP);
        profile2.setSelectedMultiselectValues(Collections.EMPTY_MAP);
        match = new Matching(random, categoryRepository,
                MeetingProposalEntity.builder()
                    .proposerProfile(profile1)
                    .build(),
                MeetingProposalEntity.builder()
                    .proposerProfile(profile2)
                    .build());
        ScoreAndCategory expected = new ScoreAndCategory(2,
                DimensionCategory.builder()
                        .description("Ernährung")
                        .build());

        when(categoryRepository.getDimensionCategoryByDescription("Ernährung")).thenReturn(Optional.ofNullable(expected.category()));

        // Act
        ScoreAndCategory actual = match.getStats();

        // Assert
        assertEquals(expected.currentScore(), actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }

    @ParameterizedTest
    @CsvSource({
            "1957, 1, Ernährung",
            "1961, 1, Ernährung",
            "1971, 2, Ernährung",
            "1977, 3, Alter"
    })
    void testScoreAndCategory_withSimilarProfilesDifferentBirthYear_shouldReturnCorrectScoreAndCategory(int birthYear, int expectedScore, String expectedCategory) {
        profile2.setSelectedBasicValues(profile1.getSelectedBasicValues());
        profile2.setSelectedWeightedValues(profile1.getSelectedWeightedValues());
        profile2.setSelectedMultiselectValues(profile1.getSelectedMultiselectValues());

        profile1.setBirthYear(1957);
        profile2.setBirthYear(birthYear);

        match = new Matching(random, categoryRepository,
                MeetingProposalEntity.builder()
                        .proposerProfile(profile1)
                        .build(),
                MeetingProposalEntity.builder()
                        .proposerProfile(profile2)
                        .build());
        ScoreAndCategory expected = new ScoreAndCategory(expectedScore,
                DimensionCategory.builder()
                        .description(expectedCategory)
                        .build());
        when(categoryRepository.getDimensionCategoryByDescription(expectedCategory)).thenReturn(Optional.ofNullable(expected.category()));

        ScoreAndCategory actual = match.getStats();

        assertEquals(expected.currentScore(), actual.currentScore());
        assertEquals(expected.category(), actual.category());
    }
}
