package de.brockhausag.diversitylunchspringboot.profile.modelTest.entities;

import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DietEntity implements BaseEntity {
    @Id
    @GeneratedValue
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
        final DietEntity other = (DietEntity) obj;
        return other.id.equals(this.id) && other.descriptor.equals(this.descriptor);
    }

}
