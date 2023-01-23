package de.brockhausag.diversitylunchspringboot.dimensions.controller.implementations.MultiSelectDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.controller.DimensionController;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.MultiSelectDimension.MultiSelectSelectableOptionCreationDTO;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.MultiSelectDimension.MultiSelectSelectableOptionUpdateDTO;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.MultiselectDimensionDto;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/multiselectdimension")
@RestController
public class MultiselectDimensionController implements DimensionController<
        MultiselectDimensionDto, MultiSelectSelectableOptionCreationDTO, MultiSelectSelectableOptionUpdateDTO,
        MultiselectDimensionSelectableOption, MultiselectDimension
        > {
    @Override
    public ResponseEntity<List<MultiselectDimensionDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSelectableOption(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateSelectableOption(MultiSelectSelectableOptionUpdateDTO multiSelectSelectableOptionUpdateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createSelectableOption(MultiSelectSelectableOptionCreationDTO multiSelectSelectableOptionCreationDTO) {
        return null;
    }
}
