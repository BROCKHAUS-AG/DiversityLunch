package de.brockhausag.diversitylunchspringboot.dimensions.entities.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Builder
public class MultiselectDimension implements Dimension<MultiselectDimensionSelectableOption> {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DimensionCategory dimensionCategory;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dimension_category_id")
    @Size(min = 1)
    private Set<MultiselectDimensionSelectableOption> selectableValues;

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
