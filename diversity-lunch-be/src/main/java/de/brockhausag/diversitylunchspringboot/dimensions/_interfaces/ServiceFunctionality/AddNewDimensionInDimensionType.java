package de.brockhausag.diversitylunchspringboot.dimensions._interfaces.ServiceFunctionality;

import de.brockhausag.diversitylunchspringboot.dimensions._interfaces.baseModel.Dimension;

public interface AddNewDimensionInDimensionType<
        BaseDimension extends Dimension
        > {

    public void createDimension(BaseDimension dimension);

}
