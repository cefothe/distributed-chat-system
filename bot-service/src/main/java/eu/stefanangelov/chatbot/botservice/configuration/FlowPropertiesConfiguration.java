package eu.stefanangelov.chatbot.botservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 28.12.18.
 */
@Data
@ConfigurationProperties
public class FlowPropertiesConfiguration {

    private Map<String, String> flow = new HashMap<>();
}
