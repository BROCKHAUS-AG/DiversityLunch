package de.brockhausag.diversitylunchspringboot.generics.defaultDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionMapper;
import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionModelController;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DefaultDimensionModelController<
        DtoType extends DefaultDimensionDto,
        EntityType extends DefaultDimensionEntity,
        RepositoryType extends CrudRepository<EntityType, Long>,
        ServiceType extends DefaultDimensionEntityService<EntityType, RepositoryType>,
        MapperType extends DimensionMapper<DtoType, EntityType>>
        extends DimensionModelController<
        DtoType, EntityType, RepositoryType, ServiceType, MapperType
        > {
    public DefaultDimensionModelController(MapperType mapper, ServiceType service) {
        super(mapper, service);
    }

    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    @PostMapping("/{id}/setDefault")
    public ResponseEntity<Void> setDefault(@PathVariable Long id) {
        ResponseEntity<Void> response;
        var success = service.setAsDefault(id);
        if(success) {
            response = ResponseEntity.ok().build();
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @Override
    public ResponseEntity<DtoType> postOne(@RequestBody DtoType dto) {
        dto.setDefault(false); // A new value must not be default
        return super.postOne(dto);
    }

    @Override
    public ResponseEntity<DtoType> putOne(@RequestBody DtoType dto) {
        var dbEntity = service.getEntityById(dto.getId());
        if(dbEntity.isPresent()) {
            dto.setDefault(dbEntity.get().isDefault());
        } else {
            dto.setDefault(false);
        }
        return super.putOne(dto);
    }

    @Override
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        var dbEntity = service.getEntityById(id);
        if (dbEntity.isEmpty())
            return ResponseEntity.notFound().build();
        else if (dbEntity.get().isDefault()) {
            return ResponseEntity.badRequest().build();
        } else {
            return super.deleteOne(id);
        }
    }
}
