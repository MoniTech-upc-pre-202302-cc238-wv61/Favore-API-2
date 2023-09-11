package com.monitech.restapi.service.impl;

import com.monitech.restapi.model.Chat;
import com.monitech.restapi.repository.ChatRepository;
import com.monitech.restapi.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(Long chat_id) {
        Optional<Chat> chat = chatRepository.findById(chat_id);
        if(chat.isPresent()) {
            return chat.get();
        }
        throw new RuntimeException("Chat not found with id: " + chat_id);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Chat updateChat(Long chat_id, Chat chat) {
        if(chatRepository.existsById(chat_id)) {
            chat.setChat_id(chat_id);
            return chatRepository.save(chat);
        }
        throw new RuntimeException("Chat not found with id: " + chat_id);
    }

    @Override
    public Chat deleteChat(Long chat_id) {
        Optional<Chat> chat = chatRepository.findById(chat_id);
        if(chat.isPresent()) {
            chatRepository.delete(chat.get());
            return chat.get();
        }
        throw new RuntimeException("Chat not found with id: " + chat_id);
    }
}
