package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingProposalEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private ProfileEntity proposerProfile;

    private LocalDateTime proposedDateTime;

    private boolean matched;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
