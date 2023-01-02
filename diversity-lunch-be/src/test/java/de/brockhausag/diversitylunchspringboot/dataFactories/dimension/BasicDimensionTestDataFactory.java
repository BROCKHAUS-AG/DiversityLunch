package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicDimensionTestDataFactory {

    public BasicDimension buildEntity(int setNumber) {
        BasicSelectableOptionTestDataFactory selectableFactory = new BasicSelectableOptionTestDataFactory();
        DimensionCategory category;
        BasicDimension result;
        switch (setNumber) {
            case 1:
                category = DimensionCategory.builder()
                        .id(1L)
                        .description("Geschlecht")
                        .profileQuestion("Geschlecht?")
                        .build();
                result = BasicDimension.builder()
                        .id(1L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 4))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 2:
                category = DimensionCategory.builder()
                        .id(2L)
                        .description("Projekt")
                        .profileQuestion("Projekt?")
                        .build();
                result = BasicDimension.builder()
                        .id(2L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 4))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            default:
                category = DimensionCategory.builder()
                        .id(10L)
                        .description("Default")
                        .profileQuestion("Default?")
                        .build();
                result = BasicDimension.builder()
                        .id(10L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, 4))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
        }
        return result;
    }

    public Set<BasicDimension> buildEntities(int amount) {
        return Stream.of(1, 2).map(this::buildEntity).limit(amount).collect(Collectors.toSet());
    }
}
