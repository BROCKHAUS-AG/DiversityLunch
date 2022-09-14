package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.DietService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.DietMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.ErrorHandlingController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/diet")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class DietController extends ErrorHandlingController {

    private final DietMapper dietMapper;
    private final DietService dietService;

    public DietController(DietMapper dietMapper, DietService dietService) {
        this.dietMapper = dietMapper;
        this.dietService = dietService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DietDto>> getAllCountries(){
        List<DietEntity> dietEntityList = dietService.getAllEntities();
        return new ResponseEntity<>(
                dietMapper.entityToDto(dietEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietDto> getDiet(@PathVariable Long id){
        Optional<DietEntity> optionalDietEntity = dietService.getEntityById(id);
        if (optionalDietEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                dietMapper.entityToDto(optionalDietEntity.get()),
                HttpStatus.OK
        );
    }
}
