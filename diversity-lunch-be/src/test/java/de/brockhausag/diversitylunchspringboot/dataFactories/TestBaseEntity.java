package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestBaseEntity implements BaseEntity {

    private Long id;

    private String descriptor;

    @Override
    public int hashCode() {
        String hashString = this.id.toString() + this.descriptor;
        return hashString.hashCode() ;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final TestBaseEntity other = (TestBaseEntity) obj;
        return other.id.equals(this.id) && other.descriptor.equals(this.descriptor);
    }

}
