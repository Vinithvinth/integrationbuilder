package com.cloudeagle.integrationbuilder.service;

import com.cloudeagle.integrationbuilder.model.ApiConfig;
import com.cloudeagle.integrationbuilder.model.FetchedUser;
import com.cloudeagle.integrationbuilder.repository.ApiConfigRepository;
import com.cloudeagle.integrationbuilder.repository.FetchedUserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ApiIntegrationService {

    @Autowired
    private ApiConfigRepository apiConfigRepository;

    @Autowired
    private FetchedUserRepository fetchedUserRepository;

    @Value("${calendly.api.token}")
    private String calendlyToken;

    public void fetchUsers(String appName) {
        Optional<ApiConfig> optionalConfig =
                apiConfigRepository.findByAppNameAndEndpointName(appName, "list_users");

        if (optionalConfig.isEmpty()) {
            throw new RuntimeException("API Config not found for " + appName);
        }

        ApiConfig config = optionalConfig.get();

        WebClient client = WebClient.builder()
                .baseUrl(config.getUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + calendlyToken)
                .build();

        String response = client.get()
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> Mono.error(new RuntimeException("API Call Failed")))
                .bodyToMono(String.class)
                .block();

        parseAndStoreUsers(response, appName);
    }

    private void parseAndStoreUsers(String response, String appName) {
        JSONObject json = new JSONObject(response);
        JSONArray users = json.getJSONArray("collection");

        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            FetchedUser fetchedUser = new FetchedUser();
            fetchedUser.setName(user.optString("name", "Unknown"));
            fetchedUser.setEmail(user.optString("email", "No Email"));
            fetchedUser.setAppName(appName);
            fetchedUserRepository.save(fetchedUser);
        }
    }
}
