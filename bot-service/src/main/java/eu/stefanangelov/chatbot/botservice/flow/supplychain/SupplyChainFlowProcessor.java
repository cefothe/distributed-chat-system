package eu.stefanangelov.chatbot.botservice.flow.supplychain;

import eu.stefanangelov.chatbot.botservice.action.ActionService;
import eu.stefanangelov.chatbot.botservice.action.to.ActionType;
import eu.stefanangelov.chatbot.botservice.flow.FlowProcessor;
import eu.stefanangelov.chatbot.botservice.nlg.NLGService;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.ontology.OntologyService;
import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import eu.stefanangelov.chatbot.botservice.supplychain.GraphQLBuilder;
import eu.stefanangelov.chatbot.botservice.supplychain.GraphQLTemplate;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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

    private final NLGService nlgService;

    private final ActionService actionService;

    private final OntologyService ontologyService;

    @Override
    public BotResponse execute(NluResponse nluResponse) {
        log.info("Execute supply chain flow");
        String graphQLQuery = graphQLBuilder.buildQuery(nluResponse);
        JSONObject response = graphQLTemplate.apply(graphQLQuery);
        ActionType action = actionService.findIntentByName(nluResponse.getIntent().getIntentName()).orElseThrow(IllegalArgumentException::new);
        ClassType ontology = ontologyService.findClassByName(action.getClazz()).orElseThrow(IllegalArgumentException::new);
        String nlgValue = nlgService.generate(response, ontology, action);
        return new BotResponse().status(BotResponse.StatusEnum.SUCCESS).content(nlgValue);
    }
}
