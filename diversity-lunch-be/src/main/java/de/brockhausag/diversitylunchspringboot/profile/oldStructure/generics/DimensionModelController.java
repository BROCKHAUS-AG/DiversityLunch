package de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import de.brockhausag.diversitylunchspringboot.utils.ErrorHandlingController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class DimensionModelController<DtoType,
        Selectable extends SelectableOptions,
        DimensionType extends Dimension<Selectable>,
        ServiceType extends DimensionService<DimensionType, Selectable>,
        MapperType extends DimensionMapper<DtoType, Selectable>>
        extends ErrorHandlingController {

    private final MapperType dimensionMapper;
    private final ServiceType dimensionService;

    private final String dimensionCategoryDescription;

    public DimensionModelController(MapperType dimensionMapper, ServiceType dimensionService, String dimensionCategoryDescription) {
        this.dimensionMapper = dimensionMapper;
        this.dimensionService = dimensionService;
        this.dimensionCategoryDescription = dimensionCategoryDescription;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoType>> getAll() {
        Optional<DimensionCategory> category = dimensionService.getDimensionCategoryByDescription(dimensionCategoryDescription);
        if (category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<Selectable> genericEntityList = dimensionService.getSelectableOptionsByCategory(category.get());
        return new ResponseEntity<>(
                dimensionMapper.entityToDto(genericEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoType> getOne(@PathVariable Long id) {
        var selectableOptional = dimensionService.getSelectableOptionById(id);
        if (selectableOptional.isPresent()) {
            return new ResponseEntity<>(
                    dimensionMapper.entityToDto(selectableOptional.get()),
                    HttpStatus.OK
            );
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<DtoType> postOne(@RequestBody DtoType dto) {
        Selectable selectable = dimensionMapper.dtoToEntity(dto);
        if (dimensionService.addSelectableOption(selectable)) {
            return ResponseEntity.ok(dimensionMapper.entityToDto((selectable)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<DtoType> putOne(@RequestBody DtoType dto) {
        Selectable selectable = dimensionMapper.dtoToEntity(dto);
        if (dimensionService.updateSelectableOption(selectable)) {
            return ResponseEntity.ok(dimensionMapper.entityToDto((selectable)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).PROFILE_OPTION_WRITE)")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        if (dimensionService.deleteSelectableOptionById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

}
