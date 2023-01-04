package de.brockhausag.diversitylunchspringboot.profile.oldStructure.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.LanguageMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/language")
@RestController
public class LanguageController extends DimensionModelController<
        LanguageDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        LanguageMapper> {
    public LanguageController(LanguageMapper mapper, BasicDimensionService service) {
        super(mapper, service, "Muttersprache");
    }
}
