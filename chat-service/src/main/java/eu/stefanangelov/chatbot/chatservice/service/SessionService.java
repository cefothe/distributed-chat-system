package eu.stefanangelov.chatbot.chatservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This service is responsible to handle all operation from web socket
 *
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@Service
public class SessionService {

    private  Map<String, List<WebSocketSession>> sessions = new ConcurrentHashMap<>();

    /**
     * Remove web socket from map
     * @param userIdentificator user identicator
     */
    public void removeSessions(String userIdentificator){
        sessions.remove(sessions);
    }

    /**
     * Add web socket with key user identificator
     * @param webSocketSession current web socket session
     * @param userIdentificator user identificator
     */
    public void addSession(WebSocketSession webSocketSession, String userIdentificator){
        sessions.computeIfAbsent(userIdentificator, value-> new ArrayList<>()).add(webSocketSession);
    }

    /**
     * Find user web socket by user identificator
     * @param userIdentificator user identificator
     * @return all open web socket session for specific user
     */
    public List<WebSocketSession> findSessionsByUserIdentificator(String userIdentificator){
        return sessions.get(userIdentificator);
    }

}
