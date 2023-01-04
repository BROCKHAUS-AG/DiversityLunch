package de.brockhausag.diversitylunchspringboot.profile.oldStructure.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.CountryMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/country")
@RestController
public class CountryController extends DimensionModelController<CountryDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        CountryMapper> {
    public CountryController(CountryMapper mapper, BasicDimensionService service) {
        super(mapper, service, "Ethnische Herkunft");
    }
}
