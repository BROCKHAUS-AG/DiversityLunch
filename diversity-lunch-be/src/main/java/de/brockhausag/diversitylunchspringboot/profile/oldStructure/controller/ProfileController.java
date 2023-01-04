package de.brockhausag.diversitylunchspringboot.profile.oldStructure.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProfileMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/profiles")
@Slf4j
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;


    @Operation(summary = "gibt das Profil des Users zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "gefundenes Profil wird zurück gegeben"),
            @ApiResponse(responseCode = "404", description = "es wurde kein Profil zu den übermittelten Daten gefunden", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "es ist ein technischer Fehler aufgetreten", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping(produces = {"application/json"}, path = "/{id}")
    @PreAuthorize("isProfileOwner(#id)")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        log.info("GET PROFILE " + id);

        Optional<ProfileEntity> entityOptional = this.profileService.getProfile(id);

        if (entityOptional.isEmpty()) {
            log.info("PROFILE NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProfileEntity entity = entityOptional.get();
        log.info("PROFILE FOUND " + entity);
        return new ResponseEntity<>(this.profileMapper.entityToDto(entityOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ACCOUNT_READ)")
    public ResponseEntity<List<ProfileDto>> getProfiles() {
        Iterable<ProfileEntity> profiles = profileService.getAllProfiles();
        List<ProfileDto> profileDtos = StreamSupport.stream(profiles.spliterator(), true)
                .map(profileMapper::entityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(profileDtos);
    }

    @Operation(summary = "die Angaben zur Registrierung eines Benutzers werden gespeichert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Angaben eines Benutzers wurden erfolgreich in der Datenbank gespeichert"),
            @ApiResponse(responseCode = "400", description = "die Daten wurden aufgrund ungültiger Daten nicht gespeichert", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "es ist ein technischer Fehler aufgetreten", content = @Content(schema = @Schema(hidden = true)))
    })

    @PostMapping("/byAccount/{accountId}")
    @PreAuthorize("isAccountOwner(#accountId)")
    public ResponseEntity<ProfileDto> createProfile(
            @Valid @RequestBody ProfileDto createProfileDto,
            @PathVariable Long accountId
    ) {
        log.debug("CREATE PROFILE OF USER: " + accountId);

        Optional<ProfileEntity> createEntityOptional = this.profileMapper.dtoToEntity(createProfileDto);

        if (createEntityOptional.isEmpty()) {
            log.error("Create profile failed for account " + accountId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<ProfileEntity> optionalEntity = this.profileService.createProfile(createEntityOptional.get(), accountId);

        if (optionalEntity.isEmpty()) {
            log.error("Create profile failed for account " + accountId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("PROFILE CREATED " + optionalEntity.get().getId());

        ProfileDto dto = this.profileMapper.entityToDto(optionalEntity.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "das Profil eines registrierten Users wurde aktualisiert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Änderungen des Benutzers wurden erfolgreich in der Datenbank gespeichert"),
            @ApiResponse(responseCode = "400", description = "die Änderungen wurden aufgrund ungültiger Daten nicht gespeichert", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "es ist ein technischer Fehler aufgetreten", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    @PreAuthorize("isProfileOwner(#id)")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileDto updateProfileDto

    ) {
        if (!updateProfileDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("UPDATE PROFILE " + updateProfileDto);
        Optional<ProfileEntity> updateEntityOptional = this.profileMapper.dtoToEntity(updateProfileDto);
        if (updateEntityOptional.isEmpty()) {
            log.error("Update profile failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<ProfileEntity> entity = this.profileService.updateProfile(updateEntityOptional.get());

        if (entity.isEmpty()) {
            log.info("Update profile failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("PROFILE UPDATED " + entity);

        ProfileDto dto = this.profileMapper.entityToDto(entity.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
