package eu.stefanangelov.chatbot.botservice.supplychain.service;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class GraphQLTemplate implements Function<String, JSONObject> {

    @Value("${supply.chain.service.url}")
    private String grapQLurl;

    private final RestTemplate restTemplate;

    @Override
    public JSONObject apply(String query) {
        return restTemplate.postForEntity(grapQLurl, query, JSONObject.class).getBody();
    }
}
