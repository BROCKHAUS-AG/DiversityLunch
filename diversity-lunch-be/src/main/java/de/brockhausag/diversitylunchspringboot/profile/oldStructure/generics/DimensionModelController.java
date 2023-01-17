package de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import de.brockhausag.diversitylunchspringboot.utils.ErrorHandlingController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DimensionModelController<DtoType,
        Selectable extends SelectableOptions,
        DimensionType extends Dimension<Selectable>,
        ServiceType extends DimensionService<DimensionType, Selectable>,
        MapperType extends DimensionMapper<DtoType, Selectable>>
        extends ErrorHandlingController {

    protected final MapperType mapper;
    protected final ServiceType service;

    private final String dimensionCategory;

    public DimensionModelController(MapperType mapper, ServiceType service, String dimensionCategory) {
        this.mapper = mapper;
        this.service = service;
        this.dimensionCategory = dimensionCategory;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoType>> getAll() {
        Long categoryId = service.getDimensionCategoryIdByDescription(dimensionCategory);
        List<Selectable> genericEntityList = service.getSelectableOptionsOfCategory(categoryId);
        return new ResponseEntity<>(
                mapper.entityToDto(genericEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoType> getOne(@PathVariable Long id) {
        Selectable selectable = service.getSelectableOptionById(id);
        return new ResponseEntity<>(
                mapper.entityToDto(selectable),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<DtoType> postOne(@RequestBody DtoType dto) {
        Selectable selectable = mapper.dtoToEntity(dto);
        service.addSelectableOption(selectable);
        return ResponseEntity.ok(mapper.entityToDto((selectable)));
    }

    @PutMapping("/")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<DtoType> putOne(@RequestBody DtoType dto) {
        Selectable selectable = mapper.dtoToEntity(dto);

        service.updateSelectableOption(selectable);

        return ResponseEntity.ok(mapper.entityToDto((selectable)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        if (service.deleteSelectableOptionById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
