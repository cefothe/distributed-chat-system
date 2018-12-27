package eu.stefanangelov.chatbot.botservice.nlu.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Range
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Range {
    private BigDecimal end;
    private BigDecimal start;
}
