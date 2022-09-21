package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyCategoryService;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.ErrorHandlingController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hobby")
@RequiredArgsConstructor
public class HobbyController extends ErrorHandlingController {

    private final HobbyMapper mapper;
    private final HobbyService service;
    private final HobbyCategoryService hobbyCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<HobbyDto>> getAll(){
        List<HobbyEntity> countryEntityList = service.getAllEntities();
        return new ResponseEntity<>(
                mapper.entityToDto(countryEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HobbyDto> getOne(@PathVariable Long id){
        Optional<HobbyEntity> optionalHobbyEntity = service.getEntityById(id);
        if (optionalHobbyEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                mapper.entityToDto(optionalHobbyEntity.get()),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<HobbyDto> put(@RequestBody HobbyDto hobbyDto){
        HobbyEntity entity = mapper.dtoToEntity(hobbyDto);

        Optional<HobbyCategoryEntity> category = hobbyCategoryService.getEntityById(entity.getCategory().getId());
        if(category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        entity.setCategory(category.get());
        entity = service.updateOrCreateEntity(entity);

        return new ResponseEntity<>(
                mapper.entityToDto(entity),
                HttpStatus.OK
        );
    }

}
