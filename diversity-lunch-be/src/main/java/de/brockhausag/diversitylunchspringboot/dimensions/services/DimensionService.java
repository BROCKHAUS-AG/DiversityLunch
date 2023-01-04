package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.DimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.SelectableOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DimensionService<
        DimensionType extends Dimension<Selectable>,
        Selectable extends SelectableOptions
>
{

    DimensionType getDimension(String categoryDescription);

    Selectable addSelectableOption(Selectable option);

     void deleteSelectableOptionById(Long selectableOptionId);

     Selectable updateSelectableOption(Selectable option);

     List<Selectable> getSelectableOptionsOfCategory(Long categoryId);

     Long getDimensionCategoryIdByDescription(String categoryDescription);

     Selectable getSelectableOptionById(Long selectableOptionId);
}
