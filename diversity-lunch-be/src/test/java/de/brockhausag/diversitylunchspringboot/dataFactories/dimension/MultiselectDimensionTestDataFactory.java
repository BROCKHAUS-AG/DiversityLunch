package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimension;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiselectDimensionTestDataFactory {
    public MultiselectDimension buildEntity(int setNumber) {
        MultiselectSelectableOptionTestDataFactory selectableFactory = new MultiselectSelectableOptionTestDataFactory();
        DimensionCategory category;
        MultiselectDimension result;
        switch (setNumber) {
            case 1:
                category = DimensionCategory.builder()
                        .id(5L)
                        .description("Hobbies")
                        .profileQuestion("Hobbies?")
                        .build();
                result = MultiselectDimension.builder()
                        .id(1L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 6))
                        .build();
                break;
            case 2:
                category = DimensionCategory.builder()
                        .id(6L)
                        .description("Haustiere")
                        .profileQuestion("Haustiere?")
                        .build();
                result = MultiselectDimension.builder()
                        .id(2L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 6))
                        .build();
                break;
            default:
                category = DimensionCategory.builder()
                        .id(12L)
                        .description("Default")
                        .profileQuestion("Default?")
                        .build();
                result = MultiselectDimension.builder()
                        .id(10L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 6))
                        .build();
        }
        return result;
    }

    public Set<MultiselectDimension> buildEntities(int amount) {
        return Stream.of(1, 2).map(this::buildEntity).limit(amount).collect(Collectors.toSet());
    }
}
