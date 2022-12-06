package de.brockhausag.diversitylunchspringboot.meeting.utils;

import de.brockhausag.diversitylunchspringboot.meeting.customValidators.ProposedDateTimeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * Validates, if the given LocalDateTime is part of a certain array of times (12:30, 13:00, 13:30) which are considered
 * to be lunchtime. Since the proposal are send in a ISO date string with GMT+0 a differentiation is needed to determine
 * whether the given ISO date is in the lunchtime for the german GMT+1 time zone. The daylight saving time
 * ("Sommerzeit", meaning summer time) making Germany effectively part of the GMT+2 zone) has to be considered as well,
 * leading to two different valid GMT+0 time arrays.
 */
public class MeetingProposalValidation implements ConstraintValidator<ProposedDateTimeValidator, LocalDateTime> {
    private static final LocalTime[] validSummerTimes = {LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(11, 30)};
    private static final LocalTime[] validWinterTimes = {LocalTime.of(11, 30), LocalTime.of(12, 0), LocalTime.of(12, 30)};

    private static boolean isSummerTime(LocalDateTime proposedDateTime) {
        final TimeZone europeBerlinTimeZone = TimeZone.getTimeZone(ZoneId.of("Europe/Berlin"));
        final Date date = Date.from(proposedDateTime.atZone(europeBerlinTimeZone.toZoneId()).toInstant());
        return europeBerlinTimeZone.inDaylightTime(date);
    }

    @Override
    public void initialize(ProposedDateTimeValidator proposedDateTimeValidator) {
    }

    @Override
    public boolean isValid(LocalDateTime proposedDateTime, ConstraintValidatorContext cxt) {
        final LocalTime[] validTimes = isSummerTime(proposedDateTime) ? validSummerTimes : validWinterTimes;
        return Arrays.stream(validTimes).anyMatch(t -> t.equals(proposedDateTime.toLocalTime()));
    }
}
