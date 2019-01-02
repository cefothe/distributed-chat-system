package eu.stefanangelov.chatbot.botservice.flow;

import eu.stefanangelov.chatbot.botservice.configuration.FlowPropertiesConfiguration;
import eu.stefanangelov.chatbot.botservice.nlu.to.Intent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

import static eu.stefanangelov.chatbot.botservice.flow.anything.AnythingFlowProcessor.FLOW_NAME;

/**
 * Factory to construct {@link FlowProcessor} depending of current action
 *
 * Created by Stefan Angelov - Delta Source Bulgaria on 28.12.18.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FlowFactoryService implements Function<Intent, FlowProcessor> {

    private final Map<String, FlowProcessor> flows;

    private final FlowPropertiesConfiguration flowPropertiesConfiguration;

    @Override
    public FlowProcessor apply(Intent intent) {
        log.info("Determinate flow for intent {}", intent.getIntentName());
        String beanFlowProcessor = flowPropertiesConfiguration.getFlow().get(intent.getIntentName());
        beanFlowProcessor = checkFlowProcessor(beanFlowProcessor);
        log.info("Determined flow is {}", beanFlowProcessor);
        return flows.get(beanFlowProcessor);
    }

    private String checkFlowProcessor(String beanFlowProcessor) {
        if (beanFlowProcessor == null) {
            log.info("Use anything else flow");
            return FLOW_NAME;
        }
        return beanFlowProcessor;
    }
}
