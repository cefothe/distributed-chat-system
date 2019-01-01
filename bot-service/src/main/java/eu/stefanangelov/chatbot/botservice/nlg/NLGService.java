package eu.stefanangelov.chatbot.botservice.nlg;

import eu.stefanangelov.chatbot.botservice.action.to.ActionType;
import eu.stefanangelov.chatbot.botservice.ontology.OntologyService;
import eu.stefanangelov.chatbot.botservice.ontology.to.AttributeType;
import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 1.01.19.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class NLGService {

    private final OntologyService ontologyService;

    public String generate(JSONObject jsonObject, ClassType classType, ActionType action) {
        log.info("Generate response");
        StringBuilder sb = new StringBuilder();
        ontologyService.findClassByName(action.getClazz()).ifPresent(clazz -> {
            JSONObject data = getGraphQLData(getGraphQLData(jsonObject, "data"), action.getName());
            String value = String.format("I found following information for %s ", clazz.getSpelling());
            sb.append(value);
            queryAttribute(action, clazz, sb, data);
            generateDataTable(sb, classType, data);
        });


        return sb.toString();
    }

    private void generateDataTable(StringBuilder sb, ClassType classType, JSONObject data) {
        sb.append("<table>");
        classType.getAttributes().getAttribute()
                .forEach(attributeType -> {
                    if (data.has(attributeType.getValue())) {
                        sb.append("<tr>");
                        sb.append("<td>");
                        sb.append(ontologyService.findClassByName(attributeType.getClazz())
                                .orElseThrow(IllegalArgumentException::new)
                                .getSpelling());
                        sb.append("</td>");
                        sb.append("<td>");
                        sb.append(data.get(attributeType.getValue()));
                        sb.append("</td>");
                        sb.append("</tr>");
                    }
                });
        sb.append("</table>");
    }

    private void queryAttribute(ActionType action, ClassType clazz, StringBuilder sb, JSONObject data) {
        AttributeType requestParam = clazz.getAttributes().getAttribute().stream()
                .filter(attributeType -> attributeType.getClazz().equals(action.getRequestParams().getParam()))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        ontologyService.findClassByName(requestParam.getClazz()).ifPresent(x -> {
            sb.append(String.format("for %s : %s ", x.getSpelling(), data.get(requestParam.getValue())));
        });
    }

    private JSONObject getGraphQLData(JSONObject jsonObject, String key) {
        return (JSONObject) jsonObject.get(key);
    }
}
