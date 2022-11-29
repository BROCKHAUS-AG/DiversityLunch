package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestDefaultDimensionEntity implements DefaultDimensionEntity {

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
        final TestDefaultDimensionEntity other = (TestDefaultDimensionEntity) obj;
        return other.id.equals(this.id) && other.descriptor.equals(this.descriptor);
    }

    public Category getQuestionCategory() {
        return Category.EDUCATION;
    }
}
