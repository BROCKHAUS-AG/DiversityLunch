package de.brockhausag.diversitylunchspringboot.profile.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CountryDto {
    private Long id;
    @Schema(description = "A country of the planet earth", example = "Deutschland")
    @Size(min=2, max=200)
    private String descriptor;
}
