package eu.stefanangelov.chatbot.botservice.nlu.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Nlu Response
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NluResponse {

    private String input;
    private Intent intent;
    private List<Slot> slots = new ArrayList<>();
}
