package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MultiselectSelectableOptionTestDataFactory {

    public static final int MULTISELECT_OPTIONS_SET_SIZE = 6;

    public MultiselectDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        return MultiselectDimensionSelectableOption.builder()
                .id((long) Math.abs(setNumber))
                .value("Option" + Math.abs(setNumber))
                .dimensionCategory(category)
                .build();
    }

    public Set<MultiselectDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return IntStream.range(1, MULTISELECT_OPTIONS_SET_SIZE + 1).boxed().limit(amount).map((setNumber) -> buildEntity(category, setNumber)).collect(Collectors.toSet());
    }
}
