package de.brockhausag.diversitylunchspringboot.dimensions.controller.implementations.BasicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.controller.DimensionController;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.BasicDimension.BasicDimensionDto;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.BasicDimension.BasicSelectableOptionCreationDTO;
import de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.BasicDimension.BasicSelectableOptionUpdateDTO;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/basicdimension")
@RestController
public class BasicDimensionController implements DimensionController<
        BasicDimensionDto, BasicSelectableOptionCreationDTO, BasicSelectableOptionUpdateDTO,
        BasicDimensionSelectableOption, BasicDimension
        > {
    @Override
    public ResponseEntity<List<BasicDimensionDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSelectableOption(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateSelectableOption(BasicSelectableOptionUpdateDTO basicSelectableOptionUpdateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createSelectableOption(BasicSelectableOptionCreationDTO basicSelectableOptionCreationDTO) {
        return null;
    }
}
