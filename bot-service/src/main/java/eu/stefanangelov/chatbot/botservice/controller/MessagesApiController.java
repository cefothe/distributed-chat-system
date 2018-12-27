package eu.stefanangelov.chatbot.botservice.controller;

import eu.stefanangelov.chatbot.botservice.to.BotResponse;
import eu.stefanangelov.chatbot.botservice.to.UserMessage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class MessagesApiController implements MessagesApi {

    @Override
    public ResponseEntity<BotResponse> messagesPost(@ApiParam(value = "Body request contain information about user message" ,required=true )  @Valid @RequestBody UserMessage body) {
        return new ResponseEntity<BotResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
