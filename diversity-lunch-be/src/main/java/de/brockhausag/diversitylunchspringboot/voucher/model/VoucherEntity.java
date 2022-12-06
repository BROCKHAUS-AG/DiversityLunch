package de.brockhausag.diversitylunchspringboot.voucher.model;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VoucherEntity {
    @GeneratedValue
    @Id
    private UUID id;

    @OneToOne
    private ProfileEntity profile;

    @NonNull
    private String voucher;

    @OneToOne
    private MeetingEntity meeting;

}
