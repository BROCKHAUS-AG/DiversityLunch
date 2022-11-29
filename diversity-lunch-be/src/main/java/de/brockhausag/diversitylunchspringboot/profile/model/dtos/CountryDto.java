package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionDto;
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
public class CountryDto implements DefaultDimensionDto {
    private Long id;

    @Schema(description = "A country of the planet earth", example = "England")
    @Size(min=2, max=100, message = "country name (descriptor) must be between 2 and 100 chars long")
    @NotNull
    @NotBlank
    private String descriptor;
}
