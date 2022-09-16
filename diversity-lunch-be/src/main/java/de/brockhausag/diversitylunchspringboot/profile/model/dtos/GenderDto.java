package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseDto;
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
public class GenderDto implements BaseDto {

    private Long id;

    @Schema(description = "the gender you identify with", example = "female")
    @Size(min=2, max=50, message = "diet descriptor must be between 2 and 50 chars long")
    @NotNull
    @NotBlank
    private String descriptor;
}
