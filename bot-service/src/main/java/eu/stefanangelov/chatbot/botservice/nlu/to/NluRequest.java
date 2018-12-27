package eu.stefanangelov.chatbot.botservice.nlu.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nlu Request
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NluRequest {
    private String content;
}
