package eu.stefanangelov.chatbot.botservice.nlg;

import eu.stefanangelov.chatbot.botservice.action.to.ActionType;
import eu.stefanangelov.chatbot.botservice.ontology.OntologyService;
import eu.stefanangelov.chatbot.botservice.ontology.to.AttributeType;
import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import eu.stefanangelov.chatbot.botservice.supplychain.QueryParamsSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.stream.StreamSupport;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 1.01.19.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class NLGService {

    private final OntologyService ontologyService;
    private final QueryParamsSession queryParamsSession;

    public String generate(JSONObject jsonObject, ClassType classType, ActionType action) {
        log.info("Generate response");
        StringBuilder sb = new StringBuilder();
        ontologyService.findClassByName(action.getClazz()).ifPresent(clazz -> {
            String value = String.format("I found following information for %s ", clazz.getSpelling());
            sb.append(value);
            queryAttribute(action, clazz, sb);
            generateResponse(getGraphQLData(jsonObject, "data"), action.getName(), (jsonData) -> generateDataTable(sb, classType, jsonData));


        });


        return sb.toString();
    }

    private void generateResponse(JSONObject jsonObject, String actionName, Consumer<JSONObject> generate) {
        Object actionData = jsonObject.get(actionName);
        if (actionData instanceof JSONObject) {
            generate.accept((JSONObject) actionData);
        }
        if (actionData instanceof JSONArray) {
            StreamSupport.stream(((JSONArray) actionData).spliterator(), false)
                    .filter(x -> x instanceof JSONObject)
                    .map(x -> (JSONObject) x)
                    .forEach(generate::accept);
        }
    }

    private void generateDataTable(StringBuilder sb, ClassType classType, JSONObject data) {
        if (classType.getIsListOf() != null) {
            classType = ontologyService.findClassByName(classType.getIsListOf()).orElseThrow(IllegalArgumentException::new);
        }
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

    private void queryAttribute(ActionType action, ClassType clazz, StringBuilder sb) {
        if (clazz.getIsListOf() != null) {
            clazz = ontologyService.findClassByName(clazz.getIsListOf()).orElseThrow(IllegalArgumentException::new);
        }
        if (action.getRequestParams() == null || action.getRequestParams().getParam() == null) {
            return;
        }
        AttributeType requestParam = clazz.getAttributes().getAttribute().stream()
                .filter(attributeType -> action.getRequestParams() != null && attributeType.getClazz().equals(action.getRequestParams().getParam()))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        ontologyService.findClassByName(requestParam.getClazz())
                .ifPresent(x -> sb.append(String.format("for %s : %s ", x.getSpelling(), queryParamsSession.requestParamValue(x))));
    }

    private JSONObject getGraphQLData(JSONObject jsonObject, String key) {
        return (JSONObject) jsonObject.get(key);
    }
}
