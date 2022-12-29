package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimensionSelectableOption;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiselectSelectableOptionTestDataFactory {

    public MultiselectDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        return switch (setNumber) {
            case 1 -> MultiselectDimensionSelectableOption.builder()
                    .id(1L)
                    .value("Option1")
                    .dimensionCategory(category)
                    .build();
            case 2 -> MultiselectDimensionSelectableOption.builder()
                    .id(2L)
                    .value("Option2")
                    .dimensionCategory(category)
                    .build();
            case 3 -> MultiselectDimensionSelectableOption.builder()
                    .id(3L)
                    .value("Option3")
                    .dimensionCategory(category)
                    .build();
            case 4 -> MultiselectDimensionSelectableOption.builder()
                    .id(4L)
                    .value("Option4")
                    .dimensionCategory(category)
                    .build();
            case 5 -> MultiselectDimensionSelectableOption.builder()
                    .id(5L)
                    .value("Option5")
                    .dimensionCategory(category)
                    .build();
            default -> MultiselectDimensionSelectableOption.builder()
                    .id(6L)
                    .value("Option6")
                    .dimensionCategory(category)
                    .build();
        };
    }

    public Set<MultiselectDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return Stream.of(1, 2, 3, 4, 5, 6).map((setNumber) -> buildEntity(category, setNumber)).limit(amount).collect(Collectors.toSet());
    }
}
