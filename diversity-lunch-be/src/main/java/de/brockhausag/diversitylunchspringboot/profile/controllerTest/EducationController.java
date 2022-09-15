package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.EducationService;
import de.brockhausag.diversitylunchspringboot.profile.mapperTest.EducationMapper;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class EducationController extends GenericBaseModelController<
        EducationDto, EducationEntity, EducationRepository, EducationService, EducationMapper> {
    public EducationController(EducationMapper mapper, EducationService service) {
        super(mapper, service);
    }
}
