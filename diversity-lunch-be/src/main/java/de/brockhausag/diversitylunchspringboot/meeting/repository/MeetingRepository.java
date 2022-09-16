package de.brockhausag.diversitylunchspringboot.meeting.repository;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Repository
public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {

    List<MeetingEntity> findByPartnerAndFromDateTimeIsAfter(@NotNull ProfileEntity entity, @NotNull LocalDateTime localDateTime);

    List<MeetingEntity> findByProposerAndFromDateTimeIsAfter(@NotNull ProfileEntity entity, @NotNull LocalDateTime localDateTime);

    List<MeetingEntity> findByFromDateTime(@NotNull LocalDateTime fromDateTime);
}
