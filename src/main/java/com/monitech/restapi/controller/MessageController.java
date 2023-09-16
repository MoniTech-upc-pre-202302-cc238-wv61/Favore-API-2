package com.monitech.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Message;
import com.monitech.restapi.service.MessageService;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/favore/v1")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    private void validateMessage(Message message) {
        if (message.getContent() == null || message.getContent().isEmpty()) {
            throw new ValidationException("Message content is required");
        }
        if (message.getSendDate() == null) {
            throw new ValidationException("Send date is required");
        }
        if (message.getChat() == null) {
            throw new ValidationException("Chat reference is required");
        }
        if (message.getSender() == null) {
            throw new ValidationException("Sender reference is required");
        }
    }

    //URL: http://localhost:8080/api/favore/v1/messages
    //Method: POST
    @Transactional
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        validateMessage(message);
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    //URL: http://localhost:8080/api/favore/v1/messages
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    //URL: http://localhost:8080/api/favore/v1/messages/{message_id}
    //Method: GET
    @Transactional
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(value = "message_id") Long message_id) {
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.ok(message);
    }

    //URL: http://localhost:8080/api/favore/v1/messages/{message_id}
    //Method: PUT
    @Transactional
    @PutMapping("/messages/{message_id}")
    public ResponseEntity<Message> updateMessage(@PathVariable(value = "message_id") Long message_id,@RequestBody Message message) {
        validateMessage(message);
        Message updatedMessage = messageService.updateMessage(message_id, message);
        return ResponseEntity.ok(updatedMessage);
    }

    //URL: http://localhost:8080/api/favore/v1/messages/{message_id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable(value = "message_id") Long message_id) {
        Message deletedMessage = messageService.deleteMessage(message_id);
        return ResponseEntity.ok(deletedMessage);
    }

}