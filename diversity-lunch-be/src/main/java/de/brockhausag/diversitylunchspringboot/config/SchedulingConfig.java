package de.brockhausag.diversitylunchspringboot.config;

import de.brockhausag.diversitylunchspringboot.match.service.MatchingService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.meeting.service.MsTeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SchedulingConfig {

    private final MatchingService matchingService;
    private final MeetingProposalRepository meetingProposalRepository;
    private final MeetingService meetingService;

    // Note: Cronjob naming scheme https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
    @Scheduled(cron = "#{'${diversity.settings.matchingCronJob}' > '' ? '${diversity.settings.matchingCronJob}' : '0 0 2 * * *'}") // Every Day at 02:00
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

    @Scheduled(cron = "#{'${diversity.settings.meetingReminderCronJob}' > '' ? '${diversity.settings.meetingReminderCronJob}' : '0 0/30 * * * *'}") // Every Full and Half Hour
    public void scheduleMailBeforeMeetingSending() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC);
        matchingService.sendQuestions(dateTime);
    }

    public void scheduleCancelDeclinedMeetings() {
        meetingService.cancelDeclinedMeetings();
    }
}
