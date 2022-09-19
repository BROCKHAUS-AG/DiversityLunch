package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.spring.autoconfigure.aad.AADAuthenticationProperties;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.Event;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.GroupCollectionPage;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMsTeamsProperties;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MicrosoftGraphService {


    private final AADAuthenticationProperties aadAuthenticationProperties;
    private final DiversityLunchMsTeamsProperties diversityLunchMsTeamsProperties;

    private GraphServiceClient<Request> setUpGraphClient(){
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

    public Event createEvent(Event event){
        GraphServiceClient<Request> graphClient = setUpGraphClient();

        String userId = diversityLunchMsTeamsProperties.getDiversityLunchUserId();

        return graphClient.users(userId).events().buildRequest().post(event);
    }

    public GroupCollectionPage getGroups() {
        GraphServiceClient<Request> graphClient = setUpGraphClient();
        return graphClient.groups().buildRequest().get();
    }
}
