package eu.stefanangelov.chatbot.chatservice.configuration;

import eu.stefanangelov.chatbot.chatservice.handler.SocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/chat").setAllowedOrigins("*");
    }
}
