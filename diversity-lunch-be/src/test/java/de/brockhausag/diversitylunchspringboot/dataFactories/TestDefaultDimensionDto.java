package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDefaultDimensionDto implements DefaultDimensionDto {

    private Long id;
    private String descriptor;
    private boolean isDefault;
}
