package de.brockhausag.diversitylunchspringboot.meeting.utils;

import de.brockhausag.diversitylunchspringboot.meeting.CustomValidators.ProposedDateTimeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Arrays;

public class MeetingProposalValidation implements ConstraintValidator<ProposedDateTimeValidator,LocalDateTime> {

    String [] validTimes = {"10:30","11:00","11:30"};

    @Override
    public void initialize(ProposedDateTimeValidator proposedDateTimeValidator) {
    }

    @Override
    public boolean isValid(LocalDateTime proposedDateTime, ConstraintValidatorContext cxt) {
        return Arrays.stream(validTimes).toList().contains(proposedDateTime.toLocalTime().toString());
    }
}
