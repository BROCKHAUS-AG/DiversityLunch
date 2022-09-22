package de.brockhausag.diversitylunchspringboot.profile.model.entities;

import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceEntity extends BaseEntity {

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final WorkExperienceEntity other = (WorkExperienceEntity) obj;
        return other.getId().equals(this.getId()) && other.getDescriptor().equals(this.getDescriptor());
    }
}
