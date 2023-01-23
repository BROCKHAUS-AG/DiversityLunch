package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private LocalDateTime fromDateTime;

    @NotNull
    @ManyToOne
    private ProfileEntity partner;

    @NotNull
    @ManyToOne
    private ProfileEntity proposer;

    @NotNull
    private int score;

    @NotNull
    @ManyToOne
    private QuestionEntity question;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    private String msTeamsMeetingId;
}
