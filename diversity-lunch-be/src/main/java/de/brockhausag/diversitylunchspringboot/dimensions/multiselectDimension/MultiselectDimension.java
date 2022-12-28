package de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultiselectDimension {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @OneToOne
    private DimensionCategory dimensionCategory;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dimension_category_id")
    @Size(min = 1)
    private List<MultiselectDimensionSelectableOption> selectableValues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MultiselectDimension that = (MultiselectDimension) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
