package de.brockhausag.diversitylunchspringboot.match.service;

import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.match.records.Match;
import de.brockhausag.diversitylunchspringboot.match.records.ScoreAndCategory;
import de.brockhausag.diversitylunchspringboot.match.utils.MatchingUtils;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
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

            List<Match> matchList = new LinkedList<>();

            for (int j = i + 1; j < meetingProposals.size(); j++) {
                secondMeetingProposal = meetingProposals.get(j);
                Match scoredMatch = getScoredMatch(firstMeetingProposal, secondMeetingProposal);
                if (scoredMatch.score() >= scoreToBeat) matchList.add(scoredMatch);
            }

            if (!matchList.isEmpty()) {
                Match bestMatch = Collections.max(matchList, Comparator.comparingInt(Match::score));
                arrangeTeamsMeeting(bestMatch);
            }
        }
    }

    private void arrangeTeamsMeeting(Match bestMatch) {
        String onlineMeetingId = msTeamsService.createMsTeamsMeeting(bestMatch);
        arrangeMeeting(bestMatch, onlineMeetingId);
    }

    private Match getScoredMatch(MeetingProposalEntity firstMeetingProposal, MeetingProposalEntity secondMeetingProposal) {
        ScoreAndCategory scoreAndCategory = MatchingUtils.getCurrentScore(firstMeetingProposal.getProposerProfile(),
                secondMeetingProposal.getProposerProfile());

        return new Match(firstMeetingProposal, secondMeetingProposal,
                scoreAndCategory.currentScore(), scoreAndCategory.category());
    }

    private void arrangeMeeting(Match bestMatch, String onlineMeetingId) {
        bestMatch.proposalOne().setMatched(true);
        bestMatch.proposalTwo().setMatched(true);
        meetingRepository.save(MeetingEntity.builder()
                .id(0)
                .fromDateTime(bestMatch.proposalOne().getProposedDateTime())
                .partner(bestMatch.proposalOne().getProposerProfile())
                .proposer(bestMatch.proposalTwo().getProposerProfile())
                .createdAt(LocalDateTime.now())
                .score(bestMatch.score())
                .question(getRandomQuestionFromCategory(bestMatch.category()))
                .msTeamsMeetingId(onlineMeetingId)
                .build()
        );
        meetingProposalRepository.save(bestMatch.proposalOne());
        meetingProposalRepository.save(bestMatch.proposalTwo());
    }

    private QuestionEntity getRandomQuestionFromCategory(Category category) throws NoSuchElementException {
        /*List<QuestionEntity> questions = questionService.getQuestionsForCategory(category.getKind());
        if (questions.size() == 0) {
            log.error("Could not find any question for category %s.".formatted(category.getKind()));
            throw new NoSuchElementException("Could not find any question for category %s.".formatted(category.getKind()));
        } else {
            int randomIndex = random.nextInt(questions.size());
            return questions.get(randomIndex);
        }*/
        return new QuestionEntity();
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
