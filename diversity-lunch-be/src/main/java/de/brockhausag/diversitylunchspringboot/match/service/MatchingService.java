package de.brockhausag.diversitylunchspringboot.match.service;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.DimensionCategoryRepository;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.match.model.Matching;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.QuestionEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MsTeamsService;
import de.brockhausag.diversitylunchspringboot.meeting.service.QuestionService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static de.brockhausag.diversitylunchspringboot.utils.DiversityLunchTemporalAdjusters.roundMinutesDownToHalfAndFull;

@RequiredArgsConstructor
@Slf4j
@Service
public class MatchingService {
    private static final Random random = new Random();
    private final MeetingProposalRepository meetingProposalRepository;
    private final MeetingRepository meetingRepository;
    private final DimensionCategoryRepository categoryRepository;
    private final DiversityLunchEMailService eMailService;
    private final MsTeamsService msTeamsService;
    private final QuestionService questionService;

    public void matching(LocalDateTime proposedDateTime, LocalDateTime today) {
        if (proposedDateTime == null || today == null) {
            throw new IllegalArgumentException("ProposedDateTime or Today equal to null!");
        }
        long daysUntilMeeting = ChronoUnit.DAYS.between(today, proposedDateTime);
        if (daysUntilMeeting > 7) {
            executeMatching(proposedDateTime, 21);
        } else if (daysUntilMeeting > 2) {
            executeMatching(proposedDateTime, 9);
        } else if (daysUntilMeeting >= 0) {
            executeMatching(proposedDateTime, 0);
        }
    }

    public void executeMatching(LocalDateTime proposedDateTime, int scoreToBeat) {
        List<MeetingProposalEntity> meetingProposals =
                meetingProposalRepository.findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(proposedDateTime);

        for (int i = 0; i < meetingProposals.size(); i++) {
            MeetingProposalEntity firstMeetingProposal = meetingProposals.get(i);
            MeetingProposalEntity secondMeetingProposal;

            if (firstMeetingProposal.isMatched()) continue;

            List<Matching> matchList = new LinkedList<>();

            for (int j = i + 1; j < meetingProposals.size(); j++) {
                secondMeetingProposal = meetingProposals.get(j);
                Matching scoredMatch = createMatching(firstMeetingProposal, secondMeetingProposal);
                if (scoredMatch.getStats().currentScore() >= scoreToBeat) matchList.add(scoredMatch);
            }

            if (!matchList.isEmpty()) {
                Matching bestMatch = Collections.max(matchList, Comparator.comparingInt((match) -> match.getStats().currentScore()));
                arrangeTeamsMeeting(bestMatch);
            }
        }
    }

    protected Matching createMatching(MeetingProposalEntity firstProposal, MeetingProposalEntity secondProposal) {
        return new Matching(random, categoryRepository, firstProposal, secondProposal);
    }

    private void arrangeTeamsMeeting(Matching bestMatch) {
        String onlineMeetingId = msTeamsService.createMsTeamsMeeting(bestMatch);
        arrangeMeeting(bestMatch, onlineMeetingId);
    }

    private void arrangeMeeting(Matching bestMatch, String onlineMeetingId) {
        bestMatch.getFirstProposal().setMatched(true);
        bestMatch.getSecondProposal().setMatched(true);
        meetingRepository.save(MeetingEntity.builder()
                .id(0)
                .fromDateTime(bestMatch.getFirstProposal().getProposedDateTime())
                .partner(bestMatch.getFirstProposal().getProposerProfile())
                .proposer(bestMatch.getSecondProposal().getProposerProfile())
                .createdAt(LocalDateTime.now())
                .score(bestMatch.getStats().currentScore())
                .question(getRandomQuestionFromCategory(bestMatch.getStats().category()))
                .msTeamsMeetingId(onlineMeetingId)
                .build()
        );
        meetingProposalRepository.save(bestMatch.getFirstProposal());
        meetingProposalRepository.save(bestMatch.getSecondProposal());
    }

    private QuestionEntity getRandomQuestionFromCategory(DimensionCategory category) throws NoSuchElementException {
        List<QuestionEntity> questions = questionService.getQuestionsForCategory(category);
        if (questions.size() == 0) {
            log.error("Could not find any question for category %s.".formatted(category.getDescription()));
            throw new NoSuchElementException("Could not find any question for category %s.".formatted(category.getDescription()));
        } else {
            int randomIndex = random.nextInt(questions.size());
            return questions.get(randomIndex);
        }
    }

    public void sendQuestions(LocalDateTime now) {
        log.debug("Sending Emails...");
        LocalDateTime modified = now.truncatedTo(ChronoUnit.MINUTES).with(roundMinutesDownToHalfAndFull()).plusHours(1);
        List<MeetingEntity> meetingEntities = meetingRepository.findByFromDateTime(modified);

        meetingEntities.forEach(meetingEntity -> {
            log.debug("Sending Email for Meeting: {} - Time: {}", meetingEntity.getId(), modified);
            try {
                ProfileEntity proposer = meetingEntity.getProposer();
                ProfileEntity partner = meetingEntity.getPartner();
                eMailService.sendEmail(proposer.getEmail(),
                        "Dein Diversity-Mittagessen",
                        eMailService.createEmailTemplateHTML(proposer, partner, meetingEntity),
                        eMailService.createEmailTemplatePlain(proposer, partner, meetingEntity));
                eMailService.sendEmail(partner.getEmail(),
                        "Dein Diversity-Mittagessen",
                        eMailService.createEmailTemplateHTML(partner, proposer, meetingEntity),
                        eMailService.createEmailTemplatePlain(partner, proposer, meetingEntity));
            } catch (Exception e) {
                log.error("Something went Wrong while Sending Emails", e);
            }
        });
    }
}
