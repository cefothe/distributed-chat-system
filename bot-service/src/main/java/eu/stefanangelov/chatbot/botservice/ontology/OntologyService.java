package eu.stefanangelov.chatbot.botservice.ontology;

import eu.stefanangelov.chatbot.botservice.ontology.to.ClassType;
import eu.stefanangelov.chatbot.botservice.ontology.to.Ontology;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 31.12.18.
 */
@Service
public class OntologyService {

    @Value("${ontology.location}")
    private Resource ontologyLocation;

    @Getter
    public Ontology ontology;

    /**
     * Find class into ontology by class name
     *
     * @param name class name
     * @return {@link Optional<ClassType>}
     */
    public Optional<ClassType> findClassByName(String name) {
        return ontology.getClazz().stream().filter(x -> name.equals(x.getName())).findAny();
    }

    @PostConstruct
    public void postConstruct() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Ontology.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        this.ontology = (Ontology) JAXBIntrospector.getValue(unmarshaller.unmarshal(ontologyLocation.getInputStream()));
    }
}
