package de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos;

import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionDto;
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
public class HobbyDto implements DimensionDto {

    private Long id;

    @Schema(description = "the hobby you practice", example = "football")
    @Size(min = 2, max = 50, message = "hobby descriptor must be between 2 and 50 chars long")
    @NotNull
    @NotBlank
    private String descriptor;
}
