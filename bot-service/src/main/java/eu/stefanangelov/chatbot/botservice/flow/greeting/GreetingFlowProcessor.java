package eu.stefanangelov.chatbot.botservice.flow.greeting;

import eu.stefanangelov.chatbot.botservice.flow.FlowProcessor;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This flow processor return predefined greeting message
 * <p>
 * Created by Stefan Angelov - Delta Source Bulgaria on 28.12.18.
 */
@Component
public class GreetingFlowProcessor implements FlowProcessor {

    @Value("${greeting.message}")
    private String greetingMessage;

    @Override
    public BotResponse execute(NluResponse nluResponse) {
        return new BotResponse().content(greetingMessage).status(BotResponse.StatusEnum.SUCCESS);
    }
}
