package de.brockhausag.diversitylunchspringboot.dimensions.entities.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"value", "dimension_category_id"}))
public class MultiselectDimensionSelectableOption implements SelectableOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String value;
    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DimensionCategory dimensionCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MultiselectDimensionSelectableOption that = (MultiselectDimensionSelectableOption) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
