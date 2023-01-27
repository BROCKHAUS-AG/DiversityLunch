package de.brockhausag.diversitylunchspringboot.dimensions.controller.implementations.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.controller.DimensionController;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.WeightedDimension.WeightedDimensionDto;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.WeightedDimension.WeightedSelectableOptionCreationDTO;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.WeightedDimension.WeightedSelectableOptionUpdateDTO;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/weighteddimension")
@RestController
public class WeightedDimensionController implements DimensionController<
        WeightedDimensionDto, WeightedSelectableOptionCreationDTO, WeightedSelectableOptionUpdateDTO,
        WeightedDimensionSelectableOption, WeightedDimension
        > {
    @Override
    public ResponseEntity<List<WeightedDimensionDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSelectableOption(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateSelectableOption(WeightedSelectableOptionUpdateDTO weightedSelectableOptionUpdateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createSelectableOption(WeightedSelectableOptionCreationDTO weightedSelectableOptionCreationDTO) {
        return null;
    }
}
