package de.brockhausag.diversitylunchspringboot.profile.utils.genericOverload;

import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseDto;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.BaseEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.ErrorHandlingController;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public class GenericControllerWithGenericMapper<
        DtoType extends BaseDto,
        EntityType extends BaseEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends GenericMapperService<EntityType, RepositoryType>,
        MapperType extends BaseModelMapper<DtoType, EntityType>>
        extends ErrorHandlingController {


    private final MapperType mapper;
    private final ServiceType service;

    public GenericControllerWithGenericMapper(MapperType mapper, ServiceType service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoType>> getAllCountries(){
        List<EntityType> countryEntityList = service.getAllEntities();
        return new ResponseEntity<>(
                mapper.entityToDto(countryEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoType> getCountry(@PathVariable Long id){
        Optional<EntityType> optionalEntityType = service.getEntityById(id);
        if (optionalEntityType.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                mapper.entityToDto(optionalEntityType.get()),
                HttpStatus.OK
        );
    }
}
