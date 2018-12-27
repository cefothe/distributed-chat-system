package eu.stefanangelov.chatbot.botservice.nlu.to;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Value
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Value {

    private String kind;
    private String value;
}
