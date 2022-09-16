package de.brockhausag.diversitylunchspringboot.profile.controller;
import de.brockhausag.diversitylunchspringboot.profile.data.LanguageRepository;

import de.brockhausag.diversitylunchspringboot.profile.logic.LanguageService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.LanguageMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.LanguageDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/language")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class LanguageController extends GenericBaseModelController<
        LanguageDto, LanguageEntity, LanguageRepository, LanguageService, LanguageMapper> {
    public LanguageController(LanguageMapper mapper, LanguageService service) {
        super(mapper, service);
    }
}
