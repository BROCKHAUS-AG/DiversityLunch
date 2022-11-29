package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.DefaultDimensionDto;
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
public class SexualOrientationDto implements DefaultDimensionDto {
    private Long id;
    @Schema(description = "The sexuality of a Person", example = "Asexual")
    @Size(min=2, max=100, message = "Sexuality (descriptor) must be between 2 and 100 chars long")
    @NotNull
    @NotBlank
    private String descriptor;
}
