package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimension;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeightedDimensionTestDataFactory {

    public WeightedDimension buildEntity(int setNumber) {
        WeightedSelectableOptionTestDataFactory selectableFactory = new WeightedSelectableOptionTestDataFactory();
        DimensionCategory category;
        WeightedDimension result;
        switch (setNumber) {
            case 1:
                category = DimensionCategory.builder()
                        .id(3L)
                        .description("Arbeitserfahrung")
                        .profileQuestion("Arbeitserfahrung?")
                        .build();
                result = WeightedDimension.builder()
                        .id(1L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 4))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 2:
                category = DimensionCategory.builder()
                        .id(4L)
                        .description("Arbeitszeit")
                        .profileQuestion("Arbeitszeit?")
                        .build();
                result = WeightedDimension.builder()
                        .id(2L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 4))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            default:
                category = DimensionCategory.builder()
                        .id(11L)
                        .description("Default")
                        .profileQuestion("Default?")
                        .build();
                result = WeightedDimension.builder()
                        .id(10L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 4))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
        }
        return result;
    }

    public Set<WeightedDimension> buildEntities(int amount) {
        return Stream.of(1, 2).map(this::buildEntity).limit(amount).collect(Collectors.toSet());
    }
}
