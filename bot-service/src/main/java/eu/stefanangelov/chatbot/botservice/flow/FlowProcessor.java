package eu.stefanangelov.chatbot.botservice.flow;

import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;

/**
 * This interface need to be implemented each flow
 * <p>
 * Created by Stefan Angelov - Delta Source Bulgaria on 27.12.18.
 */
public interface FlowProcessor {

    /**
     * Execute request to generate bot answer
     *
     * @param nluResponse Response from NLU Service
     * @return bot response
     */
    BotResponse execute(NluResponse nluResponse);

}
