package eu.stefanangelov.chatbot.botservice.supplychain;

import eu.stefanangelov.chatbot.botservice.action.ActionService;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.nlu.to.Slot;
import eu.stefanangelov.chatbot.botservice.ontology.OntologyService;
import eu.stefanangelov.chatbot.botservice.ontology.to.AttributeType;
import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 31.12.18.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GraphQLBuilder {

    private final ActionService actionService;
    private final OntologyService ontologyService;

    public String buildQuery(NluResponse nluResponse) {
        log.info("Generate GraphQL query");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"query\":\"\\n{\\n  ");
        actionService.findIntentByName(nluResponse.getIntent().getIntentName()).ifPresent(
                x -> {
                    sb.append(x.getName()).append("(");
                    if (x.getRequestParams() != null && x.getRequestParams().getParam() != null) {
                        sb.append(nameOfParam(x.getRequestParams().getParam(), ontologyService.findClassByName(x.getClazz())) + ":");
                        sb.append(slotValue(nluResponse, x.getRequestParams().getParam()));
                    }
                    sb.append(")\\n");
                    applyAttributes(sb, ontologyService.findClassByName(x.getClazz()));
                }
        );

        sb.append("\\n}\"}");

        return sb.toString();
    }

    private void applyAttributes(StringBuilder sb, Optional<ClassType> classByName) {
        classByName.ifPresent(x -> {
            sb.append("{");
            x.getAttributes().getAttribute().forEach(attributeType ->
                    sb.append(attributeType.getValue()).append("\\n")
            );
            sb.append("}");
        });
    }

    private String slotValue(NluResponse nluResponse, String entityName) {
        return nluResponse.getSlots().stream().filter(slot -> slot.getEntity().equals(entityName)).findAny()
                .map(Slot::getValue).orElseThrow(IllegalAccessError::new).getValue();
    }

    private String nameOfParam(String param, Optional<ClassType> classByName) {
        return classByName.map(clazz ->
                clazz.getAttributes().getAttribute().stream()
                        .filter(x -> x.getClazz().equals(param))
                        .findAny()
                        .orElseThrow(IllegalArgumentException::new))
                .map(AttributeType::getValue).orElseThrow(IllegalArgumentException::new);

    }
}
