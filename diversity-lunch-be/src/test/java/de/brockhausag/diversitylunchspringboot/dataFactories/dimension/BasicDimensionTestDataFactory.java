package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicDimensionTestDataFactory {
    private static final int numberOfCompleteSets = 2;
    private final static Long[] ids = {1L, 2L};
    private final static Long[] categoryIds = {1L, 2L};
    private final static String[] descriptions = {"Geschlecht", "Projekt"};
    private final static String[] questions = {"Geschlecht?", "Projekt?"};

    public BasicDimension buildEntity(int setNumber) {
        int actualSetNumber = (setNumber >= 0) && (setNumber < numberOfCompleteSets) ? setNumber : 0;
        DimensionCategory category = DimensionCategory.builder()
                .id(categoryIds[actualSetNumber])
                .description(descriptions[actualSetNumber])
                .profileQuestion(questions[actualSetNumber])
                .build();
        BasicSelectableOptionTestDataFactory selectableFactory = new BasicSelectableOptionTestDataFactory();
        return BasicDimension.builder()
                .id(ids[actualSetNumber])
                .dimensionCategory(category)
                .selectableValues(selectableFactory.buildEntities(category, 3))
                .defaultValue(selectableFactory.buildEntity(category, 0))
                .build();
    }

    public Set<BasicDimension> buildEntities(int amount) {
        return Stream.of(0, 1).map(this::buildEntity).limit(amount).collect(Collectors.toSet());
    }
}
