package de.brockhausag.diversitylunchspringboot.profile.model.entities;

import de.brockhausag.diversitylunchspringboot.profile.utils.genericOverload.TestBaseEntity;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Component
public class EducationEntity implements TestBaseEntity<EducationEntity> {
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
        final EducationEntity other = (EducationEntity) obj;
        return other.id.equals(this.id) && other.descriptor.equals(this.descriptor);
    }

    @Override
    public EducationEntity clone() {
        try {
            return (EducationEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return new EducationEntity(this.id, this.descriptor);
        }
    }
}
