package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TestDefaultDimensionDto implements DefaultDimensionDto {

    private Long id;
    private String descriptor;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
