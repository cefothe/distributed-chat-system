package eu.stefanangelov.chatbot.chatservice.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.stefanangelov.chatbot.chatservice.models.MessageStatus;
import eu.stefanangelov.chatbot.chatservice.models.MessageType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@Data
@Validated
public class MessageTO {

    @JsonProperty("userIdentificator")
    private String userIdentificator = null;

    @JsonProperty("type")
    private MessageType type = null;

    @JsonProperty("created")
    private Date created = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("status")
    private MessageStatus status = null;

}