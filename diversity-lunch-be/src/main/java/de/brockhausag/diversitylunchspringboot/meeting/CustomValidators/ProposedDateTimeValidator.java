package de.brockhausag.diversitylunchspringboot.meeting.CustomValidators;

import de.brockhausag.diversitylunchspringboot.meeting.utils.MeetingProposalValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MeetingProposalValidation.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProposedDateTimeValidator {

    String message() default "Invalid DateTime";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
