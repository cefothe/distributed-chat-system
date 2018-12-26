package eu.stefanangelov.chatbot.chatservice.respository;

import eu.stefanangelov.chatbot.chatservice.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository give us functionality to query MongoDB for entity {@link Message}
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, Long> {

}
