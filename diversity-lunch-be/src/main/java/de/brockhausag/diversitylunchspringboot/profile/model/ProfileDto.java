package de.brockhausag.diversitylunchspringboot.profile.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    @Schema(description = "die eindeutige Kennung für das Profil", example = "beispiel.email@brockhaus-ag.de")
    @Min(1)
    private long id;

    @Schema(description = "Name des Benutzers", example = "Patrick")
    @NotEmpty
    private String name;

    @Schema(description = "E-Mail des Benutzers", example = "beispiel.email@brockhaus-ag.de")
    @NotEmpty
    @Email
    private String email;

    @Schema(description = "Geburtsjahr des Benutzers", example = "1999")
    @Min(1900)
    private int birthYear;

    @Schema(description = "Aktulles Projekt des Benutzers", example = "Anderes")
    @NotNull
    private Project currentProject;

    @Schema(description = "Geschlecht des Benutzers", example = "Divers")
    @NotNull
    private Gender gender;

    @Schema(description = "Herkunftsland des Benutzers", example = "Deutschland")
    @NotNull
    private Country originCountry;

    @Schema(description = "Muttersprache des Benutzers", example = "Deutsch")
    @NotNull
    private Language motherTongue;

    @Schema(description = "Religion des Benutzers", example = "Keine Religion")
    @NotNull
    private Religion religion;

    @Schema(description = "Hobby des Benutzers", example = "Angeln")
    @NotNull
    private Hobby hobby;

    @Schema(description = "Ausbildungsweg des Benutzers", example = "Andere")
    @NotNull
    private Education education;

    @Schema(description = "Berufserfahrung des Benutzers", example = "0 - 3 Jahre")
    @NotNull
    private WorkExperience workExperience;

    @Schema(description = "Ernährung des Benutzers", example = "Andere")
    @NotNull
    private Diet diet;
}
