package de.brockhausag.diversitylunchspringboot.dimensions.controller;


import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DimensionController<DimensionDTO,
                SelectableOptionCreationDTO, SelectableOptionUpdateDTO,
                Selectable extends SelectableOptions,
                DimensionType extends Dimension<Selectable>>{

    ResponseEntity <List<DimensionDTO>> getAll();

    ResponseEntity<Void> deleteSelectableOption(Long Id);

    ResponseEntity<Void> updateSelectableOption(SelectableOptionUpdateDTO selectableOptionUpdateDTO);

    ResponseEntity<Void> createSelectableOption(SelectableOptionCreationDTO selectableOptionCreationDTO);


}
