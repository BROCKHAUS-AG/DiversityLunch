package de.brockhausag.diversitylunchspringboot.meeting.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class MeetingProposalValidationTest {
    @Mock
    private ConstraintValidatorContext cvcMock;

    @Test
    void test_11_30_isValidSummerTime() {
        LocalDateTime proposedDate_1130_1st_June_2022 = LocalDateTime.of(2022, Month.JUNE, 1, 11, 30);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1130_1st_June_2022, cvcMock);

        assertTrue(result);
    }

    @Test
    void test_11_00_isValidSummerTime() {
        LocalDateTime proposedDate_1100_1st_June_2022 = LocalDateTime.of(2022, Month.JUNE, 1, 11, 0);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1100_1st_June_2022, cvcMock);

        assertTrue(result);
    }

    @Test
    void test_10_30_isValidSummerTime() {
        LocalDateTime proposedDate_1030_1st_June_2022 = LocalDateTime.of(2022, Month.JUNE, 1, 10, 30);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1030_1st_June_2022, cvcMock);

        assertTrue(result);
    }

    @Test
    void test_12_00_isValidSummerTime() {
        LocalDateTime proposedDate_1200_1st_June_2022 = LocalDateTime.of(2022, Month.JUNE, 1, 12, 0);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1200_1st_June_2022, cvcMock);

        assertFalse(result);
    }

    @Test
    void test_12_30_isNoValidSummerTime() {
        LocalDateTime proposedDate_1230_1st_June_2022 = LocalDateTime.of(2022, Month.JUNE, 1, 12, 30);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1230_1st_June_2022, cvcMock);

        assertFalse(result);
    }

    @Test
    void test_11_30_isValidWinterTime() {
        LocalDateTime proposedDate_1130_1st_December_2022 = LocalDateTime.of(2022, Month.DECEMBER, 1, 11, 30);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1130_1st_December_2022, cvcMock);

        assertTrue(result);
    }

    @Test
    void test_12_00_isValidWinterTime() {
        LocalDateTime proposedDate_1200_1st_December_2022 = LocalDateTime.of(2022, Month.DECEMBER, 1, 12, 0);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1200_1st_December_2022, cvcMock);

        assertTrue(result);
    }

    @Test
    void test_12_30_isValidWinterTime() {
        LocalDateTime proposedDate_1230_1st_December_2022 = LocalDateTime.of(2022, Month.DECEMBER, 1, 12, 30);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1230_1st_December_2022, cvcMock);

        assertTrue(result);
    }

    @Test
    void test_10_00_isNoValidWinterTime() {
        LocalDateTime proposedDate_1000_1st_December_2022 = LocalDateTime.of(2022, Month.DECEMBER, 1, 10, 0);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1000_1st_December_2022, cvcMock);

        assertFalse(result);
    }

    @Test
    void test_10_30_isNoValidWinterTime() {
        LocalDateTime proposedDate_1030_1st_December_2022 = LocalDateTime.of(2022, Month.DECEMBER, 1, 10, 30);
        MeetingProposalValidation meetingProposalValidation = new MeetingProposalValidation();

        boolean result = meetingProposalValidation.isValid(proposedDate_1030_1st_December_2022, cvcMock);

        assertFalse(result);
    }
}
