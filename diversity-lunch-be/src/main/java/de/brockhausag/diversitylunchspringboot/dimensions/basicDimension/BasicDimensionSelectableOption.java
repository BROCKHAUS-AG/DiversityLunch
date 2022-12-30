package de.brockhausag.diversitylunchspringboot.dimensions.basicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions._interfaces.baseModel.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicDimensionSelectableOption implements SelectableOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String value;
    @NotNull
    private boolean ignoreInScoring;
    @NotNull
    @ManyToOne
    private DimensionCategory dimensionCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasicDimensionSelectableOption that = (BasicDimensionSelectableOption) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
