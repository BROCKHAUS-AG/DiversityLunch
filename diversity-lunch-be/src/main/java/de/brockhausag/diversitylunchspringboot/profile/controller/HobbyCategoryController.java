package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.HobbyCategoryRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyCategoryService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyCategoryMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyCategoryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/hobby-category")
@RestController
public class HobbyCategoryController extends DefaultDimensionModelController<
        HobbyCategoryDto, HobbyCategoryEntity, HobbyCategoryRepository, HobbyCategoryService, HobbyCategoryMapper> {
    public HobbyCategoryController(HobbyCategoryMapper mapper, HobbyCategoryService service) {
        super(mapper, service);
    }
}
