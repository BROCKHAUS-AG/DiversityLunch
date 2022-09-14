package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.EducationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.EducationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.ErrorHandlingController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class EducationController extends ErrorHandlingController {

    private final EducationMapper educationMapper;
    private final EducationService educationService;

    public EducationController(EducationMapper educationMapper, EducationService educationService) {
        this.educationMapper = educationMapper;
        this.educationService = educationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EducationDto>> getAllCountries(){
        List<EducationEntity> educationEntityList = educationService.getAllEntities();
        return new ResponseEntity<>(
                educationMapper.entityToDto(educationEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDto> getEducation(@PathVariable Long id){
        Optional<EducationEntity> optionalEducationEntity = educationService.getEntityById(id);
        if (optionalEducationEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                educationMapper.entityToDto(optionalEducationEntity.get()),
                HttpStatus.OK
        );
    }
}

