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
    private final QueryParamsSession queryParamsSession;

    public String buildQuery(NluResponse nluResponse) {
        log.info("Generate GraphQL query");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"query\":\"\\n{\\n  ");
        actionService.findIntentByName(nluResponse.getIntent().getIntentName()).ifPresent(
                x -> {
                    sb.append(x.getName());
                    if (x.getRequestParams() != null && x.getRequestParams().getParam() != null) {
                        Optional<ClassType> clazz = ontologyService.findClassByName(x.getClazz());
                        String value = slotValue(nluResponse, x.getRequestParams().getParam());
                        sb.append("(");
                        sb.append(nameOfParam(x.getRequestParams().getParam(), clazz));
                        sb.append(":");
                        sb.append(value);
                        sb.append(")\\n");
                        ClassType queryClass = ontologyService.findClassByName(classOfParam(x.getRequestParams().getParam(), clazz)).orElseThrow(IllegalArgumentException::new);
                        queryParamsSession.putParam(queryClass, value);
                    }
                    applyAttributes(sb, ontologyService.findClassByName(x.getClazz()));
                }
        );

        sb.append("\\n}\"}");

        return sb.toString();
    }

    private void applyAttributes(StringBuilder sb, Optional<ClassType> classByName) {
        classByName.ifPresent(x -> {
            ClassType clazz = x;
            if (x.getIsListOf() != null) {
                clazz = ontologyService.findClassByName(x.getIsListOf()).orElseThrow(IllegalArgumentException::new);
            }
            sb.append("{");
            clazz.getAttributes().getAttribute().forEach(attributeType ->
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

    private String classOfParam(String param, Optional<ClassType> classByName) {
        return classByName.map(clazz ->
                clazz.getAttributes().getAttribute().stream()
                        .filter(x -> x.getClazz().equals(param))
                        .findAny()
                        .orElseThrow(IllegalArgumentException::new))
                .map(AttributeType::getClazz).orElseThrow(IllegalArgumentException::new);
    }
}
