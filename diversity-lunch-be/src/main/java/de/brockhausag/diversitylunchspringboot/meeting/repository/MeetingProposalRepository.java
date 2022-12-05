package de.brockhausag.diversitylunchspringboot.meeting.repository;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingProposalRepository extends JpaRepository<MeetingProposalEntity, Long> {

    List<MeetingProposalEntity> findByProposerProfile(ProfileEntity entity);

    List<MeetingProposalEntity> findByProposerProfileAndMatchedFalseAndProposedDateTimeIsAfter(@NotNull ProfileEntity entity, @NotNull LocalDateTime localDateTime);

    List<MeetingProposalEntity> findMeetingProposalEntitiesByMatchedFalse();

    List<MeetingProposalEntity> findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(LocalDateTime proposedDateTime);

    Optional<MeetingProposalEntity> findByProposedDateTimeAndProposerProfileAndMatchedTrue(LocalDateTime proposedDateTime, ProfileEntity entity);
}
