package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EducationDto implements DimensionDto {

    private Long id;

    @Schema(description = "completed education", example = "training")
    @Size(min = 2, max = 50, message = "education descriptor must be between 2 and 50 chars long")
    @NotNull
    @NotBlank
    private String descriptor;

    @NotNull
    @Schema(description = "Default value for the given dimension")
    private boolean isDefault;

}
