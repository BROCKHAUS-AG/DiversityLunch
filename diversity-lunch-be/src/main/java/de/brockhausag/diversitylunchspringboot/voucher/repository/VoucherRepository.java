package de.brockhausag.diversitylunchspringboot.voucher.repository;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface VoucherRepository extends CrudRepository<VoucherEntity, UUID> {
    Optional<VoucherEntity> getFirstByProfileIsNullAndMeetingIsNull();
    Iterable<VoucherEntity> getAllByProfile(long profileId);
    Boolean existsByProfileAndMeeting(long profileId, long meetingId);
}
