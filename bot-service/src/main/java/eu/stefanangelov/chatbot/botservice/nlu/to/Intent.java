package eu.stefanangelov.chatbot.botservice.nlu.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Intent
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intent {
    private String intentName;
    private Double probability;
}
