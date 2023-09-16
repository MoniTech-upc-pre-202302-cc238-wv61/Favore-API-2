package com.monitech.restapi.service;

import java.util.List;
import com.monitech.restapi.model.Message;

public interface MessageService {

    Message createMessage(Message message);

    Message updateMessage(Long message_id, Message message);

    Message getMessageById(Long message_id);

    Message deleteMessage(Long message_id);

    List<Message> getAllMessages();

}