package de.brockhausag.diversitylunchspringboot.generics.BasicDimension;

import de.brockhausag.diversitylunchspringboot.utils.ErrorHandlingController;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class BaseModelController<
        DtoType extends BaseDto,
        EntityType extends BaseEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends BaseEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends ErrorHandlingController {


    private final MapperType mapper;
    private final ServiceType service;

    public BaseModelController(MapperType mapper, ServiceType service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoType>> getAll(){
        List<EntityType> genericEntityList = service.getAllEntities();
        return new ResponseEntity<>(
                mapper.entityToDto(genericEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoType> getOne(@PathVariable Long id){
        Optional<EntityType> optionalEntityType = service.getEntityById(id);
        if (optionalEntityType.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                mapper.entityToDto(optionalEntityType.get()),
                HttpStatus.OK
        );
    }

    @PostMapping
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<DtoType> postOne(@RequestBody DtoType dto){
        EntityType entity = mapper.dtoToEntity(dto);

        entity = service.createEntity(entity);

        return ResponseEntity.ok(mapper.entityToDto((entity)));
    }

    @PutMapping
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<DtoType> putOne(@RequestBody DtoType dto){
        EntityType entity = mapper.dtoToEntity(dto);

        entity = service.updateOrCreateEntity(entity);

        return ResponseEntity.ok(mapper.entityToDto((entity)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        if(service.deleteEntityById(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
