package eu.stefanangelov.chatbot.botservice.nlu.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nlu Response
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NluResponse implements Serializable {

    private String input;
    private Intent intent;
    private List<Slot> slots = new ArrayList<>();
}
