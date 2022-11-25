package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.DietRepository;

import de.brockhausag.diversitylunchspringboot.profile.logic.DietService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.DietMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/diet")
@RestController
public class DietController extends BaseModelController<
        DietDto, DietEntity, DietRepository, DietService, DietMapper> {
    public DietController(DietMapper mapper, DietService service) {
        super(mapper, service);
    }
}
