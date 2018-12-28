package eu.stefanangelov.chatbot.botservice.flow.goodbye;

import eu.stefanangelov.chatbot.botservice.flow.FlowProcessor;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This flow processor return predefined goodbye message
 * <p>
 * Created by Stefan Angelov - Delta Source Bulgaria on 28.12.18.
 */
@Component
public class GoodbyeFlowProcessor implements FlowProcessor {

    @Value("${goodbye.message}")
    private String goodbyeMessage;

    @Override
    public BotResponse execute(NluResponse nluResponse) {
        return new BotResponse().content(goodbyeMessage).status(BotResponse.StatusEnum.SUCCESS);
    }
}
