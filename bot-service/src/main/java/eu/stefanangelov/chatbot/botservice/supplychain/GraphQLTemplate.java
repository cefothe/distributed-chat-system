package eu.stefanangelov.chatbot.botservice.supplychain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

/**
 * This service is responsible to send request to graphQL service and return back.
 * <p>
 * Created by Stefan Angelov - Delta Source Bulgaria on 30.12.18.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GraphQLTemplate implements Function<String, JSONObject> {

    @Value("${supply.chain.service.url}")
    private String grapQLurl;

    private final RestTemplate restTemplate;

    @Override
    public JSONObject apply(String query) {
        log.info("Send GraphqlQL query");
        String jsonResponse = restTemplate.postForEntity(grapQLurl, query, String.class).getBody();
        return new JSONObject(jsonResponse);
    }
}
