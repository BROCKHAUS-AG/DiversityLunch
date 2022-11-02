package de.brockhausag.diversitylunchspringboot.meeting.utils;

import de.brockhausag.diversitylunchspringboot.meeting.CustomValidators.ProposedDateTimeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * Validates, if the given LocalDateTime is part of a certain array of times (12:30, 13:00, 13:30) which are considered
 * to be lunchtime. Since the proposal are send in a ISO date string with GMT+0 a differentiation is needed to determine
 * whether the given ISO date is in the lunchtime for the german GMT+1 time zone. The daylight saving time
 * ("Sommer" meaning summer time, making Germany effectively part of the GMT+2 zone) has to be considered as well,
 * leading to two different valid GMT+0 time arrays.
 */
public class MeetingProposalValidation implements ConstraintValidator<ProposedDateTimeValidator,LocalDateTime> {
    private static final String [] validSummerTimes = {"10:30","11:00","11:30"};
    private static final String [] validWinterTimes = {"11:30","12:00","12:30"};

    @Override
    public void initialize(ProposedDateTimeValidator proposedDateTimeValidator) {
    }

    @Override
    public boolean isValid(LocalDateTime proposedDateTime, ConstraintValidatorContext cxt) {
        String [] validTimes = checkSummerTime(proposedDateTime) ? validSummerTimes : validWinterTimes;
        return Arrays.stream(validTimes).toList().contains(proposedDateTime.toLocalTime().toString());
    }

    private boolean checkSummerTime(LocalDateTime proposedDateTime) {
        Date date = Date.from(proposedDateTime.atZone(ZoneId.of("Europe/Berlin")).toInstant());
        return TimeZone.getDefault().inDaylightTime( date );
    }
}
