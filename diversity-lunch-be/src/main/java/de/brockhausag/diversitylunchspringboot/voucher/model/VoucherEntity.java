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
@Builder
@Getter
@Setter
public class VoucherEntity {
    @GeneratedValue
    @Id
    private UUID uuid;

    @OneToOne
    private ProfileEntity profile;

    private String voucherCode;

    @OneToOne
    private MeetingEntity meeting;
}
