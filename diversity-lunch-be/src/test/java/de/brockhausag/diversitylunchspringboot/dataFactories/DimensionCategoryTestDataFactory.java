package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;

public class DimensionCategoryTestDataFactory {
    DimensionCategory buildEntity () {
        return buildEntity("Test-Category");
    }

    DimensionCategory buildEntity (String descriptor) {
        var dimensionCategory = new DimensionCategory();

        dimensionCategory.setId(1L);
        dimensionCategory.setDescriptor(descriptor);

        return dimensionCategory;
    }
}
