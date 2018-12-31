package eu.stefanangelov.chatbot.botservice.supplychain;

import eu.stefanangelov.chatbot.botservice.action.ActionService;
import eu.stefanangelov.chatbot.botservice.nlu.to.NluResponse;
import eu.stefanangelov.chatbot.botservice.ontology.OntologyService;
import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 31.12.18.
 */
@RequiredArgsConstructor
@Service
public class GraphQLBuilder {

    private final ActionService actionService;
    private final OntologyService ontologyService;

    public String buildQuery(NluResponse nluResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"query\":\"\\n{\\n  ");
        actionService.findIntentByName(nluResponse.getIntent().getIntentName()).ifPresent(
                x -> {
                    sb.append(x.getName() + "(");
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
            x.getAttributes().getAttribute().stream().forEach(attributeType -> {
                sb.append(attributeType.getValue() + "\\n");
            });
            sb.append("}");
        });
    }

    private String slotValue(NluResponse nluResponse, String entityName) {
        return nluResponse.getSlots().stream().filter(slot -> slot.getEntity().equals(entityName)).findAny().get().getValue().getValue();
    }

    private String nameOfParam(String param, Optional<ClassType> classByName) {
        ClassType classType = classByName.get();
        return classType.getAttributes().getAttribute().stream()
                .filter(attributeType -> attributeType.getClazz().equals(param))
                .findAny().get().getValue();
    }
}
