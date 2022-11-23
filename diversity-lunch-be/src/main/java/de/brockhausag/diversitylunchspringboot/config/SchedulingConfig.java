package de.brockhausag.diversitylunchspringboot.config;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.match.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
@Profile("!Test")
public class SchedulingConfig {

    private final MatchingService matchingService;
    private final MeetingProposalRepository meetingProposalRepository;


    @Scheduled(cron = "0 0 2 * * *")
    public void scheduleMatching() {
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = date.atTime(0, 0);
        List<MeetingProposalEntity> proposalEntities = this.meetingProposalRepository.findAll();
        List<LocalDateTime> allMatchTimes = proposalEntities
                .stream()
                .map(MeetingProposalEntity::getProposedDateTime)
                .distinct()
                .toList();
        allMatchTimes.forEach(time -> matchingService.matching(time, dateTime));
    }

    @Scheduled(cron = "0 0/30 * * * *") // Every Full and Half Hour
    public void scheduleMailBeforeMeetingSending() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC);
        matchingService.sendQuestions(dateTime);
    }
}
