package de.brockhausag.diversitylunchspringboot.match.service;

import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Question;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.match.utils.Match;
import de.brockhausag.diversitylunchspringboot.match.utils.MatchingUtils;
import de.brockhausag.diversitylunchspringboot.match.utils.ScoreAndCategory;
import de.brockhausag.diversitylunchspringboot.meeting.service.MsTeamsService;
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

    private final MeetingProposalRepository meetingProposalRepository;
    private final MeetingRepository meetingRepository;
    private final DiversityLunchEMailService eMailService;
    private final MsTeamsService msTeamsService;
    private static final Random random = new Random();

    public void matching(LocalDateTime proposedDateTime, LocalDateTime today){
        if(proposedDateTime == null || today == null ){
            throw new IllegalArgumentException("ProposedDateTime or Today equal to null!");
        }
        long daysUntilMeeting = ChronoUnit.DAYS.between(today, proposedDateTime);
        if(daysUntilMeeting > 7) {
            executeMatching(proposedDateTime, 21);
        }else if(daysUntilMeeting > 2){
            executeMatching(proposedDateTime, 9);
        }else if(daysUntilMeeting >= 0){
            executeMatching(proposedDateTime, 0);
        }

    }

    public void executeMatching(LocalDateTime proposedDateTime, int scoreToBeat) {
        List<MeetingProposalEntity> meetingProposals =
                meetingProposalRepository.findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(proposedDateTime);

        for (int i = 0; i < meetingProposals.size(); i++) {


            List<Match> matchList = new LinkedList<>();

            for (int j = i + 1; j < meetingProposals.size(); j++) {
                ScoreAndCategory scoreAndCategory = MatchingUtils.getCurrentScore(meetingProposals.get(i).getProposerProfile(),
                        meetingProposals.get(j).getProposerProfile());
                Match match = new Match(meetingProposals.get(i), meetingProposals.get(j),
                       scoreAndCategory.currentScore(), scoreAndCategory.category());
                if (match.score() >= scoreToBeat) {
                    matchList.add(match);
                }
            }
            if (!matchList.isEmpty()) {
                Match bestMatch = Collections.max(matchList, Comparator.comparingInt(Match::score));
                String onlineMeetingId = msTeamsService.createMsTeamsMeeting(bestMatch);
                arrangeMeeting(bestMatch, onlineMeetingId);
                meetingProposals.remove(bestMatch.proposalTwo());
            }
        }
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

    private Question getRandomQuestionFromCategory(Category category) {
        List<Question> questions = Question.getAllQuestionsWithCategory(category);
        int randomIndex = random.nextInt(questions.size());
        return questions.get(randomIndex);
    }

    public void sendQuestions(LocalDateTime now) {
        String BASE_URL="https://diversitylunch.brockhaus-ag.de";
        String link = BASE_URL + "/api/vouchers/%d/%d";
        log.debug("Sending Emails...");
        LocalDateTime modified = now.truncatedTo(ChronoUnit.MINUTES).with(roundMinutesDownToHalfAndFull()).plusHours(1);
        List<MeetingEntity> meetingEntities = meetingRepository.findByFromDateTime(modified);
        meetingEntities.forEach(meetingEntity -> {
            log.debug("Sending Email for Meeting: {} - Time: {}", meetingEntity.getId(), modified);
            try {
                ProfileEntity[] partner = {meetingEntity.getPartner(), meetingEntity.getProposer()};
                eMailService.sendEmail(partner[0].getEmail(),
                        "Dein Diversity-Mittagessen", eMailService.createEmailTemplateHTML(partner[0].getName(), partner[1].getName(),
                                meetingEntity.getQuestion(),String.format(link,partner[0].getId(),meetingEntity.getId())), eMailService.createEmailTemplatePlain(partner[0].getName(), partner[1].getName(),
                                meetingEntity.getQuestion(),String.format(link,partner[0].getId(),meetingEntity.getId())));
                eMailService.sendEmail(partner[1].getEmail(),
                        "Dein Diversity-Mittagessen", eMailService.createEmailTemplateHTML(partner[1].getName(), partner[0].getName(),
                                meetingEntity.getQuestion(),String.format(link,partner[1].getId(),meetingEntity.getId())), eMailService.createEmailTemplatePlain(partner[1].getName(), partner[0].getName(),
                                meetingEntity.getQuestion(),String.format(link,partner[1].getId(),meetingEntity.getId())));
            } catch (Exception e) {
                log.error("Something went Wrong while Sending Emails", e);
            }
        });
    }
}
