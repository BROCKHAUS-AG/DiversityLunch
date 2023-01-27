package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MultiselectDimensionTestDataFactory {

    public static final int MULTISELECT_SET_SIZE = 1;

    public MultiselectDimension buildEntity(int setNumber) {
        MultiselectSelectableOptionTestDataFactory selectableFactory = new MultiselectSelectableOptionTestDataFactory();
        DimensionCategory category;
        MultiselectDimension result;
        switch (setNumber) {
            case 1:
                category = DimensionCategory.builder()
                        .id(12L)
                        .description("Hobby")
                        .profileQuestion("Hobby?")
                        .build();
                result = MultiselectDimension.builder()
                        .id(1L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, MultiselectSelectableOptionTestDataFactory.MULTISELECT_OPTIONS_SET_SIZE))
                        .build();
                break;
            default:
                category = DimensionCategory.builder()
                        .id(120L)
                        .description("Default")
                        .profileQuestion("Default?")
                        .build();
                result = MultiselectDimension.builder()
                        .id(120L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, MultiselectSelectableOptionTestDataFactory.MULTISELECT_OPTIONS_SET_SIZE))
                        .build();
        }
        return result;
    }

    public Set<MultiselectDimension> buildEntities(int amount) {
        return IntStream.range(1, MULTISELECT_SET_SIZE + 1).boxed().limit(amount).map(this::buildEntity).collect(Collectors.toSet());
    }
}
