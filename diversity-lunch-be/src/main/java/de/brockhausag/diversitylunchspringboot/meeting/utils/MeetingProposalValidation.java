package de.brockhausag.diversitylunchspringboot.meeting.utils;

import de.brockhausag.diversitylunchspringboot.meeting.CustomValidators.ProposedDateTimeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class MeetingProposalValidation implements ConstraintValidator<ProposedDateTimeValidator,LocalDateTime> {
    String [] validSummerTimes = {"10:30","11:00","11:30"};
    String [] validWinterTimes = {"11:30","12:00","12:30"};

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
