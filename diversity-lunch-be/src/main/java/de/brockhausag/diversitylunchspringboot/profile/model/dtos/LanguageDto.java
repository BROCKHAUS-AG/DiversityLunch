package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseDto;
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
public class LanguageDto implements BaseDto {

    private Long id;

    @Schema(description = "an official language of the planet earth", example = "english")
    @Size(min=2, max=50, message = "language descriptor must be between 2 and 50 chars long")
    @NotNull
    @NotBlank
    private String descriptor;
}
