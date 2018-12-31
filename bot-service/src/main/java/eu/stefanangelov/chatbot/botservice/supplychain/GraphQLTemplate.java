package eu.stefanangelov.chatbot.botservice.supplychain;

import lombok.RequiredArgsConstructor;
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
public class GraphQLTemplate implements Function<String, String> {

    @Value("${supply.chain.service.url}")
    private String grapQLurl;

    private final RestTemplate restTemplate;

    @Override
    public String apply(String query) {
        return restTemplate.postForEntity(grapQLurl, query, String.class).getBody();
    }
}
