package eu.stefanangelov.chatbot.botservice.nlu.to;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Slot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

    private String entity;

    private Range range;

    private String rawValue;

    private String slotName;

    private Value value;

}
