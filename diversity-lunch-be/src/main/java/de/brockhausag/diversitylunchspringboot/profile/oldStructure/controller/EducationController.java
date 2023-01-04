package de.brockhausag.diversitylunchspringboot.profile.oldStructure.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.EducationMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/education")
@RestController
public class EducationController extends DimensionModelController<
        EducationDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        EducationMapper> {
    public EducationController(EducationMapper mapper, BasicDimensionService service) {

        super(mapper, service, "Bildungsweg");
    }
}
