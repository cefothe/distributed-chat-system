package eu.stefanangelov.chatbot.chatservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 26.12.18.
 */
@Service
public class SessionService {

    private  Map<String, List<WebSocketSession>> sessions = new ConcurrentHashMap<>();

    public void removeSessions(String userIdentificator){
        sessions.remove(sessions);
    }

    public void addSession(WebSocketSession webSocketSession, String userIdentificator){
        sessions.computeIfAbsent(userIdentificator, value-> new ArrayList<>()).add(webSocketSession);
    }

    public List<WebSocketSession> findSessionsByUserIdentificator(String userIdentificator){
        return sessions.get(userIdentificator);
    }

}
