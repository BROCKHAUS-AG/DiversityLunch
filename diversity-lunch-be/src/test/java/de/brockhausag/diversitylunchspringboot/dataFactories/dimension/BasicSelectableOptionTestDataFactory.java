package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicSelectableOptionTestDataFactory {
    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {1L, 2L, 3L};
    private static final String[] values = {"Option1", "Option2", "Option3"};
    private static final boolean[] ignoreInScorings = {true, false, false};

    public BasicDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        int actualSetNumber = (setNumber >= 0) && (setNumber < numberOfCompleteSets) ? setNumber : 0;
        return BasicDimensionSelectableOption.builder()
                .id(ids[actualSetNumber])
                .value(values[actualSetNumber])
                .ignoreInScoring(ignoreInScorings[actualSetNumber])
                .dimensionCategory(category)
                .build();
    }

    public Set<BasicDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return Stream.of(0, 1, 2).map((setNumber) -> buildEntity(category, setNumber)).limit(amount).collect(Collectors.toSet());
    }
}
