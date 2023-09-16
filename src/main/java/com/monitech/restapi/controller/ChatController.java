package com.monitech.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Chat;
import com.monitech.restapi.service.ChatService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/favore/v1")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    private void validateChat(Chat chat) {
        if (chat.getUser1() == null || chat.getUser2() == null) {
            throw new ValidationException("Both users are required for a chat");
        }
        if (chat.getCreationDate() == null) {
            throw new ValidationException("Creation date is required");
        }
    }

    //URL: http://localhost:8080/api/favore/v1/chats
    //Method: POST
    @Transactional
    @PostMapping("/chats")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        validateChat(chat);
        Chat createdChat = chatService.createChat(chat);
        return ResponseEntity.ok(createdChat);
    }

    //URL: http://localhost:8080/api/favore/v1/chats
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getAllChats() {
        List<Chat> chats = chatService.getAllChats();
        return ResponseEntity.ok(chats);
    }

    //URL: http://localhost:8080/api/favore/v1/chats/1
    //Method: GET
    @Transactional
    @GetMapping("/chats/{chat_id}")
    public ResponseEntity<Chat> getChatById(@PathVariable(value = "chat_id") Long chat_id) {
        Chat chat = chatService.getChatById(chat_id);
        return ResponseEntity.ok(chat);
    }

    //URL: http://localhost:8080/api/favore/v1/chats/1
    //Method: PUT
    @Transactional
    @PutMapping("/chats/{chat_id}")
    public ResponseEntity<Chat> updateChat(@PathVariable(value = "chat_id") Long chat_id,@RequestBody Chat chat) {
        validateChat(chat);
        Chat updatedChat = chatService.updateChat(chat_id, chat);
        return ResponseEntity.ok(updatedChat);
    }

    //URL: http://localhost:8080/api/favore/v1/chats/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/chats/{chat_id}")
    public ResponseEntity<Chat> deleteChat(@PathVariable(value = "chat_id") Long chat_id) {
        Chat deletedChat = chatService.deleteChat(chat_id);
        return ResponseEntity.ok(deletedChat);
    }
}
