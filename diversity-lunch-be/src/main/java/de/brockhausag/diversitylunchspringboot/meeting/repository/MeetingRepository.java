package de.brockhausag.diversitylunchspringboot.meeting.repository;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {

    List<MeetingEntity> findByPartnerAndFromDateTimeIsAfter(@NotNull ProfileEntity entity, @NotNull LocalDateTime localDateTime);

    List<MeetingEntity> findByProposerAndFromDateTimeIsAfter(@NotNull ProfileEntity entity, @NotNull LocalDateTime localDateTime);

    List<MeetingEntity> findByFromDateTime(@NotNull LocalDateTime fromDateTime);

    Optional<MeetingEntity> findByMsTeamsMeetingId(@NotNull String meetingId);
}
