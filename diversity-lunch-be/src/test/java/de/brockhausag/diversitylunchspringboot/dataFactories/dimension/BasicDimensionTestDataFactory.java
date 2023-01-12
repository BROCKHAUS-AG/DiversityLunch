package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicDimensionTestDataFactory {

    public static final int BASIC_SET_SIZE = 10;

    public BasicDimension buildEntity(int setNumber) {
        BasicSelectableOptionTestDataFactory selectableFactory = new BasicSelectableOptionTestDataFactory();
        DimensionCategory category;
        BasicDimension result;
        switch (setNumber) {
            case 1:
                category = DimensionCategory.builder()
                        .id(1L)
                        .description("Projekt")
                        .profileQuestion("Projekt?")
                        .build();
                result = BasicDimension.builder()
                        .id(1L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 2:
                category = DimensionCategory.builder()
                        .id(2L)
                        .description("Geschlechtliche Identität")
                        .profileQuestion("Geschlechtliche Identität?")
                        .build();
                result = BasicDimension.builder()
                        .id(2L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 3:
                category = DimensionCategory.builder()
                        .id(3L)
                        .description("Ethnische Herkunft")
                        .profileQuestion("Ethnische Herkunft?")
                        .build();
                result = BasicDimension.builder()
                        .id(3L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 4:
                category = DimensionCategory.builder()
                        .id(4L)
                        .description("Religion")
                        .profileQuestion("Religion?")
                        .build();
                result = BasicDimension.builder()
                        .id(4L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 5:
                category = DimensionCategory.builder()
                        .id(5L)
                        .description("Muttersprache")
                        .profileQuestion("Muttersprache?")
                        .build();
                result = BasicDimension.builder()
                        .id(5L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 6:
                category = DimensionCategory.builder()
                        .id(6L)
                        .description("Bildungsweg")
                        .profileQuestion("Bildungsweg?")
                        .build();
                result = BasicDimension.builder()
                        .id(6L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 7:
                category = DimensionCategory.builder()
                        .id(7L)
                        .description("Ernährung")
                        .profileQuestion("Ernährung?")
                        .build();
                result = BasicDimension.builder()
                        .id(7L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 8:
                category = DimensionCategory.builder()
                        .id(8L)
                        .description("Soziale Herkunft")
                        .profileQuestion("Soziale Herkunft?")
                        .build();
                result = BasicDimension.builder()
                        .id(8L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 9:
                category = DimensionCategory.builder()
                        .id(9L)
                        .description("Diskriminierung aufgrund sozialer Herkunft")
                        .profileQuestion("Diskriminierung aufgrund sozialer Herkunft?")
                        .build();
                result = BasicDimension.builder()
                        .id(9L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            case 10:
                category = DimensionCategory.builder()
                        .id(10L)
                        .description("Sexuelle Orientierung")
                        .profileQuestion("Sexuelle Orientierung?")
                        .build();
                result = BasicDimension.builder()
                        .id(10L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
                break;
            default:
                category = DimensionCategory.builder()
                        .id(100L)
                        .description("Default")
                        .profileQuestion("Default?")
                        .build();
                result = BasicDimension.builder()
                        .id(100L)
                        .dimensionCategory(category)
                        .selectableValues(selectableFactory.buildEntities(category, BasicSelectableOptionTestDataFactory.BASIC_OPTIONS_SET_SIZE))
                        .defaultValue(selectableFactory.buildEntity(category, 0))
                        .build();
        }
        return result;
    }

    public Set<BasicDimension> buildEntities(int amount) {
        return IntStream.range(1, BASIC_SET_SIZE + 1).boxed().limit(amount).map(this::buildEntity).collect(Collectors.toSet());
    }
}
