package com.monitech.restapi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monitech.restapi.model.Message;
import com.monitech.restapi.repository.MessageRepository;
import com.monitech.restapi.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Long message_id, Message message) {
        if (messageRepository.existsById(message_id)) {
            message.setMessage_id(message_id);
            return messageRepository.save(message);
        } else {
            throw new RuntimeException("Message not found with id " + message_id);
        }
    }

    @Override
    public Message getMessageById(Long message_id) {
        return messageRepository.findById(message_id).orElse(null);
    }

    @Override
    public Message deleteMessage(Long message_id) {
        Message message = getMessageById(message_id);
        if (message != null) {
            messageRepository.deleteById(message_id);
            return message;
        } else {
            throw new RuntimeException("Message not found with id " + message_id);
        }
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

}