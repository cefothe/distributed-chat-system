package eu.stefanangelov.chatbot.botservice.nlu.service;

import eu.stefanangelov.chatbot.botservice.nlu.to.NluRequest;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.to.UserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * This service is responsible to make REST call to NLU Service
 * Created by Stefan Angelov - Delta Source Bulgaria on 27.12.18.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class NLURestService{

    private final RestTemplate restTemplate;

    @Value("${nlu.service.url}")
    public String nluServiceURI;

    /**
     * Send rest post request to NLU service
     *
     * @param userMessage incoming user message
     * @return response from REST call
     */
    @Cacheable(cacheNames = "NLU")
    public NluResponse apply(UserMessage userMessage) {
        Assert.notNull(userMessage, "User message can't be null");
        Assert.notNull(userMessage.getContent(), "Content from user can't be null");
        NluRequest nluRequest = new NluRequest(userMessage.getContent());
        log.info("Send request to NLU Service");
        return restTemplate.postForEntity(nluServiceURI, nluRequest, NluResponse.class).getBody();
    }
}
