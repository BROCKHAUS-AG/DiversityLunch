package de.brockhausag.diversitylunchspringboot.dimensions.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;

public interface DimensionController<DimensionType extends Dimension<Selectable>, Selectable extends SelectableOptions> {
}
