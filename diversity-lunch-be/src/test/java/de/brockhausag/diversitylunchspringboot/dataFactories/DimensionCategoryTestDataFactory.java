package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.generics.dimensionCategory.DimensionCategoryEntity;

public class DimensionCategoryTestDataFactory {
    DimensionCategoryEntity buildEntity () {
        return buildEntity("Test-Category");
    }

    DimensionCategoryEntity buildEntity (String descriptor) {
        var dimensionCategory = new DimensionCategoryEntity();

        dimensionCategory.setId(1L);
        dimensionCategory.setDescriptor(descriptor);

        return dimensionCategory;
    }
}
