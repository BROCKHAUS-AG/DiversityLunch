package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.ErrorHandlingController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hobby")
public class HobbyController extends ErrorHandlingController {

    private final HobbyMapper mapper;
    private final HobbyService service;

    public HobbyController(HobbyMapper mapper, HobbyService service) {
        this.mapper = mapper;
        this.service = service;
    }

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
}
