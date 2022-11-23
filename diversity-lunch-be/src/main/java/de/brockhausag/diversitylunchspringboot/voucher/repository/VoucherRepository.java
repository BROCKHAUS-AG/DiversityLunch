package de.brockhausag.diversitylunchspringboot.voucher.repository;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoucherRepository extends JpaRepository<VoucherEntity, UUID> {
    Optional<VoucherEntity> getFirstByProfileIsNullAndMeetingIsNull();
    int countAllByProfileIsNotNullAndMeetingIsNotNull();
    int countAllByProfileIsNullAndMeetingIsNull();

    Optional<VoucherEntity> getVoucherEntityByProfileIdAndMeetingId(long profile_Id, long meeting_Id);
    List<VoucherEntity> getAllByProfileId(long profile_Id);
    boolean existsByProfileIdAndMeetingId(long profile_Id, long meeting_Id);

}
