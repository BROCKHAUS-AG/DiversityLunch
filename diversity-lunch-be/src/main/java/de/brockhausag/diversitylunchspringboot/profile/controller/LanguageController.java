package de.brockhausag.diversitylunchspringboot.profile.controller;
import de.brockhausag.diversitylunchspringboot.profile.data.LanguageRepository;

import de.brockhausag.diversitylunchspringboot.profile.logic.LanguageService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.LanguageMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/language")
@RestController
public class LanguageController extends BaseModelController<
        LanguageDto, LanguageEntity, LanguageRepository, LanguageService, LanguageMapper> {
    public LanguageController(LanguageMapper mapper, LanguageService service) {
        super(mapper, service);
    }
}
