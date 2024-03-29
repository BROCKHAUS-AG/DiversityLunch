package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WeightedDimensionTestDataFactory {

    public static final int WEIGHTED_SET_SIZE = 1;

    public WeightedDimension buildEntity(int setNumber) {
        WeightedSelectableOptionTestDataFactory selectableFactory = new WeightedSelectableOptionTestDataFactory();
        DimensionCategory category;
        WeightedDimension result;
        switch (setNumber) {
            case 1:
                category = DimensionCategory.builder()
                        .id(11L)
                        .description("Berufserfahrung")
                        .profileQuestion("Berufserfahrung?")
                        .build();
                result = WeightedDimension.builder()
                        .id(1L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, WeightedSelectableOptionTestDataFactory.WEIGHTED_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            default:
                category = DimensionCategory.builder()
                        .id(110L)
                        .description("Default")
                        .profileQuestion("Default?")
                        .build();
                result = WeightedDimension.builder()
                        .id(110L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, WeightedSelectableOptionTestDataFactory.WEIGHTED_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
        }
        return result;
    }

    public Set<WeightedDimension> buildEntities(int amount) {
        return IntStream.range(1, WEIGHTED_SET_SIZE + 1).boxed().limit(amount).map(this::buildEntity).collect(Collectors.toSet());
    }
}
