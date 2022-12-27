package de.brockhausag.diversitylunchspringboot.dimensions.basicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BasicDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @OneToOne
    private DimensionCategory dimensionCategory;
    @OneToMany()
    @JoinColumn(name = "dimension_category_id")
    @Size(min = 1)
    @ToString.Exclude
    private Set<BasicDimensionSelectableOption> selectableValues;
    @NotNull
    @OneToOne
    @ToString.Exclude
    private BasicDimensionSelectableOption defaultValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasicDimension dimension = (BasicDimension) o;
        return id != null && Objects.equals(id, dimension.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
