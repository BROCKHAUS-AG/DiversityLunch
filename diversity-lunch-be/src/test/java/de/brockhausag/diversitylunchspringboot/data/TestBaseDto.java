package de.brockhausag.diversitylunchspringboot.data;

import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TestBaseDto implements BaseDto {

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
