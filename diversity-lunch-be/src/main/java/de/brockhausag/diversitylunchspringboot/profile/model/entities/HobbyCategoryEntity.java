package de.brockhausag.diversitylunchspringboot.profile.model.entities;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class HobbyCategoryEntity implements DefaultDimensionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descriptor;

    @Override
    public int hashCode() {
        String hashString = this.id.toString() + this.descriptor;
        return hashString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final HobbyCategoryEntity other = (HobbyCategoryEntity) obj;
        return other.id.equals(this.id) && other.descriptor.equals(this.descriptor);
    }

    public Category getQuestionCategory() {
        return Category.HOBBY;
    }
}
