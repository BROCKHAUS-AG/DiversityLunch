package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.utils.ErrorHandlingController;
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

    @GetMapping("/all")
    public ResponseEntity<List<HobbyDto>> getAll() {
        List<HobbyEntity> hobbyEntityList = service.getAllEntities();
        return new ResponseEntity<>(
                mapper.entityToDto(hobbyEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HobbyDto> get(@PathVariable Long id) {
        Optional<HobbyEntity> optionalHobbyEntity = service.getEntityById(id);
        if (optionalHobbyEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                mapper.entityToDto(optionalHobbyEntity.get()),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<HobbyDto> put(@RequestBody HobbyDto hobbyDto) {
        Optional<HobbyEntity> optionalHobbyEntity = mapper.dtoToEntity(hobbyDto);
        if (optionalHobbyEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HobbyEntity hobbyEntity = service.updateOrCreateEntity(optionalHobbyEntity.get());
        return new ResponseEntity<>(
                mapper.entityToDto(hobbyEntity),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<HobbyDto> post(@RequestBody HobbyDto hobbyDto) {
        Optional<HobbyEntity> optionalHobbyEntity = mapper.dtoToEntity(hobbyDto);
        if (optionalHobbyEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HobbyEntity hobbyEntity = service.createEntity(optionalHobbyEntity.get());
        return new ResponseEntity<>(
                mapper.entityToDto(hobbyEntity),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.deleteEntityById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
