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
public class SocialBackgroundDto implements DimensionDto {
    private Long id;

    @Schema(description = "Your socialBackground in years", example = "2 Jahre")
    @Size(min = 1, max = 255, message = "socialBackground must be between 1 and 255 chars long")
    @NotNull
    @NotBlank
    private String descriptor;

    @NotNull
    @Schema(description = "Default value for the given dimension")
    private boolean isDefault;
}
