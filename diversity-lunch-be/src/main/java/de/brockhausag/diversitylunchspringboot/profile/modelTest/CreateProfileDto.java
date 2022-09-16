package de.brockhausag.diversitylunchspringboot.profile.modelTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileDto {

    private String name;

    @Email
    private String email;

    @Min(1900)
    private int birthYear;

    @NotNull
    private Project currentProject;

    @NotNull
    private Gender gender;

    @NotNull
    private Country originCountry;

    @NotNull
    private Language motherTongue;

    @NotNull
    private Religion religion;

    @NotNull
    private Hobby hobby;

    @NotNull
    private Education education;

    @NotNull
    private WorkExperience workExperience;

    @NotNull
    private Diet diet;

}
