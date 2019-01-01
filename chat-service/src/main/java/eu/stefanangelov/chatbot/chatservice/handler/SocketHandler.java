package eu.stefanangelov.chatbot.chatservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.stefanangelov.chatbot.chatservice.service.MessageService;
import eu.stefanangelov.chatbot.chatservice.service.SessionService;
import eu.stefanangelov.chatbot.chatservice.to.MessageTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@RequiredArgsConstructor
@Component
public class SocketHandler extends TextWebSocketHandler {

    private final SessionService sessionService;

    private final MessageService messageService;

    private final ObjectMapper objectMapper;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        MessageTO messageTO = objectMapper.readValue(message.getPayload(), MessageTO.class);
        messageTO.setCreated(new Date());

        messageService.processUserMessage(messageTO);

        // send user message to all participant in that session
        for(WebSocketSession webSocketSession : sessionService.findSessionsByUserIdentificator(messageTO.getUserIdentificator())) {
            webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageTO)));
        }

        MessageTO botResponse = messageService.askBot(messageTO);

        // send user message to all participant in that session
        for(WebSocketSession webSocketSession : sessionService.findSessionsByUserIdentificator(messageTO.getUserIdentificator())) {
            webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(botResponse)));
        }

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Assert.notNull(session.getHandshakeHeaders(), "Hand shake headers can't be null");
        sessionService.addSession(session, "1111111111");
    }
}
