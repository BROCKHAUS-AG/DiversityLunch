package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.generics.multiDimension.MultiDimensionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HobbyDto implements MultiDimensionDto {

    private Long id;

    @Schema(description = "the hobby you practice", example = "football")
    @Size(min = 2, max = 50, message = "hobby descriptor must be between 2 and 50 chars long")
    @NotNull
    @NotBlank
    private String descriptor;

    @NotNull
    private boolean isDefault;
}
