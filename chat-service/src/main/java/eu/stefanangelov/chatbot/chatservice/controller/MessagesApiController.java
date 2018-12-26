package eu.stefanangelov.chatbot.chatservice.controller;

import eu.stefanangelov.chatbot.chatservice.models.Message;
import eu.stefanangelov.chatbot.chatservice.respository.MessageRepository;
import eu.stefanangelov.chatbot.chatservice.to.MessageTO;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MessagesApiController implements MessagesApi {

    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<MessageTO>> messagesGet(@ApiParam(value = "subset of the results to return") @Valid @RequestParam(value = "queryRange", required = false) Integer queryRange) {
        List<MessageTO> messages = messageRepository.findAll().stream().map(x -> modelMapper.map(x, MessageTO.class)).collect(Collectors.toList());
        return  ResponseEntity.ok(messages);
    }

}
