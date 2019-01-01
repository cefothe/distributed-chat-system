package eu.stefanangelov.chatbot.chatservice.service;

import eu.stefanangelov.chatbot.chatservice.models.Message;
import eu.stefanangelov.chatbot.chatservice.respository.MessageRepository;
import eu.stefanangelov.chatbot.chatservice.to.MessageTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * This service is responsible to handle all operation for messages
 *
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    @Value("${bot.service.url}")
    private String botServiceUrl;

    /**
     * Process user message {@link MessageTO} and save it into database
     * @param messageTO incoming message from web socket
     */
    public void processUserMessage(MessageTO messageTO){
        log.info("Persist user message with user indentificator {}", messageTO.getUserIdentificator());
        Message message = modelMapper.map(messageTO, Message.class);
        messageRepository.save(message);
        messageTO.setMessageId(message.getMessageId());
        log.info("Message is successfully persistent");
    }

    /**
     * Send user message to bot service and save it into database
     * @param messageTO incoming user message from web socket
     * @return return response from chat service
     */
    public MessageTO askBot(MessageTO messageTO){
        log.info("Send message to bot");
        ResponseEntity<MessageTO> botResponse = restTemplate.postForEntity(botServiceUrl, messageTO, MessageTO.class);
        log.info("Save bot response into database");
        Message message = modelMapper.map(botResponse.getBody(), Message.class);
        message.setCreated(new Date());
        messageRepository.save(message);
        log.info("Bot response is successfully persistent");
        return  botResponse.getBody();
    }

}
