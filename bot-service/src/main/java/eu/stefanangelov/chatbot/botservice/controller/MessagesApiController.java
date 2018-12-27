package eu.stefanangelov.chatbot.botservice.controller;

import eu.stefanangelov.chatbot.botservice.service.BotService;
import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import eu.stefanangelov.chatbot.botservice.to.UserMessage;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class MessagesApiController implements MessagesApi {

    private final BotService botService;

    @Override
    public ResponseEntity<BotResponse> messagesPost(@ApiParam(value = "Body request contain information about user message", required = true) @Valid @RequestBody UserMessage userMessage) {
        return ResponseEntity.ok(botService.apply(userMessage));

    }

}
