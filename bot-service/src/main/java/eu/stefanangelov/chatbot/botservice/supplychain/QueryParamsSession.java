package eu.stefanangelov.chatbot.botservice.supplychain;

import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 2.01.19.
 */
@Component
@RequestScope
public class QueryParamsSession {

    public final Map<ClassType, String> requestParams;

    public QueryParamsSession() {
        this.requestParams = new HashMap<>();
    }

    public String requestParamValue(ClassType classType) {
        return requestParams.get(classType);
    }

    public void putParam(ClassType classType, String value) {
        requestParams.put(classType, value);
    }
}
