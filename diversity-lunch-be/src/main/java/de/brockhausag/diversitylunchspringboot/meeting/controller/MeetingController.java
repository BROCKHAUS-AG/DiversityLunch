package de.brockhausag.diversitylunchspringboot.meeting.controller;

import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
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
import java.util.NoSuchElementException;
import java.util.Optional;


@RequestMapping("/api/meetings")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MeetingController {
    private final MeetingService meetingService;
    private final MeetingMapper meetingMapper;

    @Operation(summary = "die anstehenden Meetings eines Benutzers werden ausgegeben")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "die anstehenden Meetings werden ausgegeben"),
    })
    @PreAuthorize("isProfileOwner(#id)")
    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<MeetingDto>> getMeetingProposalsByUser(
            @PathVariable Long id
    ) {
        List<MeetingDto> meetingDtos = meetingService.getAllMeetingsInFutureForUser(id);

        return new ResponseEntity<>(meetingDtos, HttpStatus.OK);
    }

    @Operation(summary = "die Meetings eines registrierten Benutzers werden gespeichert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ein Meeting des Benutzers wurde gespeichert"),
            @ApiResponse(responseCode = "400", description = "das Meeting wurde aufgrund ungültiger Daten nicht gespeichert", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "es existiert bereits ein Meeting mit dem selben Datum"),
            @ApiResponse(responseCode = "500", description = "es ist ein technischer Fehler aufgetreten", content = @Content(schema = @Schema(hidden = true)))
    })
    @PreAuthorize("isProfileOwner(#id)")
    @PostMapping("byUser/{id}")
    public ResponseEntity<MeetingDto> createMeetingProposal(
            @PathVariable Long id,
            @Valid @RequestBody CreateMeetingProposalDto createMeetingProposalDto
    ) {

        if(meetingService.meetingExists(id ,createMeetingProposalDto.getFromDateTime())) {
            log.error("Meeting already exists: " + id.toString() + " Date: " + createMeetingProposalDto.getFromDateTime());
            return ResponseEntity.status(409).build();
        }

        Optional<MeetingProposalEntity> createEntity = this.meetingMapper.mapCreateDtoToEntity(createMeetingProposalDto, id);

        return createEntity
                .map(meetingService::createMeetingProposal)
                .map(meetingMapper::mapEntityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "die Terminvorschläge für ein Meeting eines registrierten Benutzers werden gelöscht")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ein Terminvorschlag für ein Meeting wurde aus der Datenbank gelöscht"),
            @ApiResponse(responseCode = "403", description = "für diesen Terminvorschlag existiert bereits ein aktives Meeting, deshalb kann es nicht gelöscht werden"),
    })
    @PreAuthorize("isProposalOwner(#id)")
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteMeetingProposal(@PathVariable Long id) {

        if(this.meetingService.checkIfMeetingProposalIsMatched(id))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        this.meetingService.deleteMeetingProposal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Das angegebene Meeting wird für den User abgesagt.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "der angegebene Termin wird aus der Datenbank gelöscht, dem Partner wird wieder als ungematched angezeigt"),
            @ApiResponse(responseCode = "400", description = "der Termin oder einer der User konnte nicht gefunden werden"),
    })
    @PreAuthorize("isMeetingsParticipant(#meetingId, #userId)")
    @PostMapping("/{userId}/cancel/{meetingId}")
    public @ResponseBody
    ResponseEntity<String> cancelMeeting(@PathVariable Long userId, @PathVariable Long meetingId) {
        // NOTE(dianati): We know who the user is (OAauth) gives us the current user
        try {
            boolean couldCancel = this.meetingService.cancelMeeting(meetingId, userId);
            if(!couldCancel)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
