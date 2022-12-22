package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.spring.autoconfigure.aad.AADAuthenticationProperties;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.EventCollectionPage;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.GroupCollectionPage;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMsTeamsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MicrosoftGraphService {


    private final AADAuthenticationProperties aadAuthenticationProperties;
    private final DiversityLunchMsTeamsProperties diversityLunchMsTeamsProperties;

    private GraphServiceClient<Request> setUpGraphClient() {
        String clientId = aadAuthenticationProperties.getClientId();
        String clientSecret = diversityLunchMsTeamsProperties.getClientSecret();
        String tenantId = aadAuthenticationProperties.getTenantId();

        List<String> scopes = List.of("https://graph.microsoft.com/.default");


        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();

        final TokenCredentialAuthProvider tokenCredAuthProvider =
                new TokenCredentialAuthProvider(scopes, clientSecretCredential);

        return GraphServiceClient
                .builder()
                .authenticationProvider(tokenCredAuthProvider)
                .buildClient();
    }

    public Event createEvent(Event event) {
        GraphServiceClient<Request> graphClient = setUpGraphClient();

        String userId = diversityLunchMsTeamsProperties.getDiversityLunchUserId();

        return graphClient.users(userId).events().buildRequest().post(event);
    }

    public void cancelEvent(String eventId, String cancellationMessage)
    {
        GraphServiceClient<Request> graphClient = setUpGraphClient();
        String userId = diversityLunchMsTeamsProperties.getDiversityLunchUserId();
        EventCancelParameterSet cancelMessage = EventCancelParameterSet.newBuilder()
                .withComment(cancellationMessage)
                .build();
        graphClient.users(userId).events(eventId).cancel(cancelMessage).buildRequest().post();
    }

    public List<Event> getAllFutureEvents() {
        GraphServiceClient<Request> graphClient = setUpGraphClient();
        String userId = diversityLunchMsTeamsProperties.getDiversityLunchUserId();
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        EventCollectionPage eventCollectionPage = graphClient.users(userId)
                .calendar()
                .events()
                .buildRequest()
                .select("attendees, start")
                .filter("start/dateTime ge '" + dateTimeString +"'")
                .top(50)
                .get();

        return eventCollectionPage == null ? Collections.emptyList() : eventCollectionPage.getCurrentPage();
    }

    public static List<Event> filterDeclinedEvents(List<Event> eventList) {
        List<Event> filteredEvents = new ArrayList<>();
        for(Event e: eventList) {
            if(e == null || e.attendees == null) continue;
            boolean hasDeclined = false;
            for(Attendee attendee: e.attendees) {
                if(attendee.status == null) continue;
                hasDeclined = attendee.status.response == ResponseType.DECLINED || hasDeclined;
            }
            if(hasDeclined) {
                filteredEvents.add(e);
            }
        }

        return filteredEvents;
    }

    public Optional<List<Group>> getGroups() {
        GraphServiceClient<Request> graphClient = setUpGraphClient();
        GroupCollectionPage groupCollectionPage = graphClient.groups().buildRequest().get();
        return groupCollectionPage != null ? Optional.of(groupCollectionPage.getCurrentPage()) : Optional.empty();
    }

/* TODO test with correct permissions    public void test() {
        GraphServiceClient<Request> graphClient = setUpGraphClient();
        String userId = "c5b1bdf1-f22e-49ad-bbac-db73e31340a4";

        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        EventCollectionPage eventCollectionPage = graphClient.users(userId)
                .calendar()
                .events()
                .buildRequest()
                .filter("start/dateTime ge '" + dateTimeString +"'")
                .top(10)
                .get();
        for (Event event : eventCollectionPage.getCurrentPage()) {
            log.info(event.subject);
        }
    }*/
}
