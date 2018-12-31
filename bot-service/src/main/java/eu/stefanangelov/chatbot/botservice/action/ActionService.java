package eu.stefanangelov.chatbot.botservice.action;

import eu.stefanangelov.chatbot.botservice.action.to.ActionType;
import eu.stefanangelov.chatbot.botservice.action.to.Actions;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 31.12.18.
 */
@Service
public class ActionService {

    @Value("${actions.location}")
    private String actionsLocation;

    @Getter
    public Actions actions;

    /**
     * Find class into ontology by action name
     *
     * @param name class name
     * @return {@link Optional < ActionType >}
     */
    public Optional<ActionType> findIntentByName(String name) {
        return actions.getAction().stream().filter(x -> name.equals(x.getName())).findAny();
    }

    @PostConstruct
    public void postConstruct() throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Actions.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        this.actions = (Actions) JAXBIntrospector.getValue(unmarshaller.unmarshal(ResourceUtils.getFile(actionsLocation)));
    }
}
