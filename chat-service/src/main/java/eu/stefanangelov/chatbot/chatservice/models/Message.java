package eu.stefanangelov.chatbot.chatservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * This class represent MongoDB schema that represent message history
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@Data
@Document(collection = "messages")
public class Message {

    @Id
    private String messageId;

    private String userIndentificator;

    private MessageType type;

    private Date created;

    private String content;

    private MessageStatus status;

}
