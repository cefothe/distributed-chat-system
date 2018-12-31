package eu.stefanangelov.chatbot.botservice.flow.supplychain;

import eu.stefanangelov.chatbot.botservice.flow.FlowProcessor;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.supplychain.GraphQLBuilder;
import eu.stefanangelov.chatbot.botservice.supplychain.GraphQLTemplate;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 28.12.18.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SupplyChainFlowProcessor implements FlowProcessor {

    private final GraphQLTemplate graphQLTemplate;

    private final GraphQLBuilder graphQLBuilder;

    @Override
    public BotResponse execute(NluResponse nluResponse) {
        String graphQLQuery = graphQLBuilder.buildQuery(nluResponse);
        String response = graphQLTemplate.apply(graphQLQuery);
        log.info(response);
        return null;
    }
}
