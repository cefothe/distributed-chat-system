package eu.stefanangelov.chatbot.botservice.service;

import eu.stefanangelov.chatbot.botservice.nlu.service.NLURestService;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import eu.stefanangelov.chatbot.botservice.to.UserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This service is responsible to handle all bot operation like take NLU result,
 * determinate flow, and generate response to user
 * <p>
 * Created by Stefan Angelov - Delta Source Bulgaria on 27.12.18.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BotService implements Function<UserMessage, BotResponse> {

    private final NLURestService nluRestService;

    @Override
    public BotResponse apply(UserMessage userMessage) {
        log.info("Process message with id {} for user with identificator {}", userMessage.getMessageId(), userMessage.getUserIdentificator());
        ResponseEntity<NluResponse> nluResponse = nluRestService.apply(userMessage);
        return null;
    }
}
