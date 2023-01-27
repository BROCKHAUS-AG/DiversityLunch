package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicSelectableOptionTestDataFactory {

    public static final int BASIC_OPTIONS_SET_SIZE = 4;

    public BasicDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        return switch (setNumber) {
            case 1 -> BasicDimensionSelectableOption.builder()
                    .id((category.getId() - 1) * BASIC_OPTIONS_SET_SIZE + setNumber)
                    .value("Option1")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .build();
            case 2 -> BasicDimensionSelectableOption.builder()
                    .id((category.getId() - 1) * BASIC_OPTIONS_SET_SIZE + setNumber)
                    .value("Option2")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .build();
            case 3 -> BasicDimensionSelectableOption.builder()
                    .id((category.getId() - 1) * BASIC_OPTIONS_SET_SIZE + setNumber)
                    .value("Option3")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .build();
            default -> BasicDimensionSelectableOption.builder()
                    .id(category.getId() * BASIC_OPTIONS_SET_SIZE)
                    .value("DefaultOption")
                    .ignoreInScoring(true)
                    .dimensionCategory(category)
                    .build();
        };
    }

    public Set<BasicDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return IntStream.range(0, BASIC_OPTIONS_SET_SIZE).boxed().limit(amount).map((setNumber) -> buildEntity(category, setNumber)).collect(Collectors.toSet());
    }
}
