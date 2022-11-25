package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.GenderRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.GenderService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.GenderMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/gender")
@RestController
public class GenderController extends BaseModelController<
        GenderDto, GenderEntity, GenderRepository, GenderService, GenderMapper> {
    public GenderController(GenderMapper mapper, GenderService service) {
        super(mapper, service);
    }
}
