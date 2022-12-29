package de.brockhausag.diversitylunchspringboot.config;

import de.brockhausag.diversitylunchspringboot.match.service.MatchingService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfig {

    private final MatchingService matchingService;
    private final MeetingProposalRepository meetingProposalRepository;
    private final MeetingService meetingService;
    //private final MicrosoftGraphService microsoftGraphService;
    private final ProfileService profileService;

    // Note: Cronjob naming scheme https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
    @Scheduled(cron = "${diversity.settings.scheduling.matchingCronJob}") // Every Day at 02:00
    public void scheduleMatching() {
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = date.atTime(0, 0);
        List<MeetingProposalEntity> proposalEntities = this.meetingProposalRepository.findAll();
        List<LocalDateTime> allMatchTimes = proposalEntities
                .stream()
                .map(MeetingProposalEntity::getProposedDateTime)
                .distinct()
                .toList();
        log.info("Running scheduled Matching at " + dateTime);
        allMatchTimes.forEach(time -> matchingService.matching(time, dateTime));
    }

    @Scheduled(cron = "${diversity.settings.scheduling.meetingReminderCronJob}") // Every Day at 02:00
    public void scheduleMailBeforeMeetingSending() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC);
        log.info("Running scheduled sending of Meeting-Reminders at " + dateTime);
        matchingService.sendQuestions(dateTime);
    }

    @Scheduled(cron = "${diversity.settings.scheduling.cancelDeclinedMeetingsCronJob}")
    public void scheduleCancelDeclinedMeetings() {
        meetingService.cancelDeclinedMeetings();
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void test() {
        profileService.getAllProfiles().forEach(profile -> {
            log.warn("\nWeighted:" + profile.getSelectedWeightedValues().toString() +
                    "\nMulti:" +  profile.getSelectedMultiselectValues().toString());
        });
    }
}
