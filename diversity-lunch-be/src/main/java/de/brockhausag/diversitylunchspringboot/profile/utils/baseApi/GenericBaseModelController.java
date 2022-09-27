package de.brockhausag.diversitylunchspringboot.profile.utils.baseApi;

import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GenericBaseModelController<
        DtoType extends BaseDto,
        EntityType extends BaseEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends GenericBaseEntityService<EntityType, RepositoryType>,
        MapperType extends Mapper<DtoType, EntityType>>
        extends ErrorHandlingController {


    private final MapperType mapper;
    private final ServiceType service;

    public GenericBaseModelController(MapperType mapper, ServiceType service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoType>> getAll(){
        List<EntityType> countryEntityList = service.getAllEntities();
        return new ResponseEntity<>(
                mapper.entityToDto(countryEntityList),
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
}
