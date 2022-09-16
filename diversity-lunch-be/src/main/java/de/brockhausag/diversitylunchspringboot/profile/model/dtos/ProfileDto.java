package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.profile.model.Hobby;
import de.brockhausag.diversitylunchspringboot.profile.model.WorkExperience;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ProfileDto {

    private Long id;

    @Schema(description = "name of user")
    @Size(min=2, max=50, message = "name must be between 2 and 50 chars long")
    @NotNull
    @NotBlank
    private String name;

    @Schema(description = "e-mail of the user", example = "example.mail@brockhaus-ag.de")
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Schema(description = "year of birth", example = "1999")
    @Min(value = 1900, message = "year of birth must be greater than 1900")
    private int birthYear;



    @NotNull
    private List<ProjectEntity> projects;
    @NotNull
    private GenderEntity gender;
    @NotNull
    private CountryEntity originCountry;
    @NotNull
    private List<LanguageEntity> motherTongue;
    @NotNull
    private ReligionEntity religion;
    @NotNull
    private EducationEntity education;
    @NotNull
    private DietEntity diet;


    @Schema(description = "a hobby you are practicing", example = "football")
    @NotNull
    private Hobby hobby;

    @Schema(description = "how long you working", example = "0 - 3 years")
    @NotNull
    private WorkExperience workExperience;

}
