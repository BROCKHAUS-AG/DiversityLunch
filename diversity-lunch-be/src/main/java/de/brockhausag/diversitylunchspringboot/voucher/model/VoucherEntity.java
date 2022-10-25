package de.brockhausag.diversitylunchspringboot.voucher.model;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    private UUID uuid;

    @OneToOne
    private ProfileEntity profile;

    @NonNull
    private String voucherCode;

    @OneToOne
    private MeetingEntity meeting;

}
