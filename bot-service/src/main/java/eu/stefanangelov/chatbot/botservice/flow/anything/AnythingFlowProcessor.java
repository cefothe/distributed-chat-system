package eu.stefanangelov.chatbot.botservice.flow.anything;

import eu.stefanangelov.chatbot.botservice.flow.FlowProcessor;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 28.12.18.
 */
@Component
public class AnythingFlowProcessor implements FlowProcessor {

    @Value("${anything.message}")
    public String message;

    public static final String FLOW_NAME = "anythingFlowProcessor";

    @Override
    public BotResponse execute(NluResponse nluResponse) {
        return new BotResponse().content(message).status(BotResponse.StatusEnum.FAILURE);
    }
}
